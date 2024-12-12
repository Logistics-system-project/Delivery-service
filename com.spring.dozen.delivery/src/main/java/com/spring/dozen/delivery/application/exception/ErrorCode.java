package com.spring.dozen.delivery.application.exception;

import static org.springframework.http.HttpStatus.*;

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



	;
	private final HttpStatus httpStatus;
	private final String message;
}
