package com.spring.dozen.delivery.application.exception;

import static org.springframework.http.HttpStatus.*;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	DUPLICATED_DELIVERY_STAFF(CONFLICT, "이미 존재하는 배송 담당자입니다."),
	UNSUPPORTED_STAFF_TYPE(NOT_FOUND, "존재하지 않는 배송 담당자 타입입니다."),
	MISSING_HUB_ID(BAD_REQUEST, "Hub ID는 필수 입력 값입니다."),
	INVALID_SEARCH_CONDITION(BAD_REQUEST, "유효하지 않은 검색 기준입니다."),
	DELIVERY_STAFF_NOT_FOUND(NOT_FOUND, "존재하지 않는 배송 담당자입니다."),
	DELIVERY_STAFF_HUB_NOT_FOUND(NOT_FOUND, "존재하지 않는 배송 담당자 허브 정보입니다."),
	DELIVERY_NOT_FOUND(NOT_FOUND, "존재하지 않는 배송 정보입니다."),

	ACCESS_DENIED(FORBIDDEN, "접근 권한이 없습니다."),
	MISSING_ROLE(BAD_REQUEST, "권한 정보가 없습니다."),

	;
	private final HttpStatus httpStatus;
	private final String message;
}
