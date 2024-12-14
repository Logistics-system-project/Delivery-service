package com.spring.dozen.delivery.application.aspect;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.spring.dozen.delivery.application.annotation.RequireRole;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleCheckAspect {

	private final HttpServletRequest request;

	@Before("@annotation(requireRole)")
	public void checkRole(RequireRole requireRole) {
		// 헤더에서 role 정보 가져오기
		String role = request.getHeader("X-Role");
		if (role == null) {
			throw new DeliveryException(ErrorCode.MISSING_ROLE);
		}

		log.debug("Checking role {}", role);
		// 허용된 권한 체크
		List<String> allowedRoles = List.of(requireRole.value());
		if (!allowedRoles.contains(role)) {
			throw new DeliveryException(ErrorCode.ACCESS_DENIED);
		}
	}
}
