package com.spring.dozen.delivery.application.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DeliveryException extends RuntimeException {
	private final HttpStatus httpStatus;
	private final String message;

	public DeliveryException(ErrorCode errorCode){
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}
