package com.spring.dozen.delivery.application.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.spring.dozen.delivery.presentation.dto.PaginationRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageUtil {

	public static Pageable toPageable(PaginationRequest request) {
		// page 검증: null 또는 음수일 경우 기본값 0
		int validatedPage = (request.page() != null && request.page() >= 0) ? request.page() : 0;

		// size 검증: null 또는 허용되지 않은 값일 경우 기본값 10
		int validatedSize = (request.size() != null && (request.size() == 10 || request.size() == 30 || request.size() == 50))
			? request.size()
			: 10;

		// sortedBy 검증: null 또는 허용되지 않은 값일 경우 기본값 "createdAt"
		String validatedSortedBy = (request.sortedBy() != null &&
			(request.sortedBy().equals("createdAt") || request.sortedBy().equals("updatedAt")))
			? request.sortedBy()
			: "createdAt";


		// isAsc 검증: null일 경우 기본값 false
		boolean isAscending = request.isAsc() != null ? request.isAsc() : false;

		// 정렬 설정
		Sort sortOrder = Sort.by(validatedSortedBy);
		if (!isAscending) {
			sortOrder = sortOrder.descending();
		}

		return PageRequest.of(validatedPage, validatedSize, sortOrder);
	}
}
