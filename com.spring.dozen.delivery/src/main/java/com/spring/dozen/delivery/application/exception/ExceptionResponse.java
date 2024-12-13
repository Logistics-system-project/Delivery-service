package com.spring.dozen.delivery.application.exception;

public record ExceptionResponse(
	String message
) {
	public String toWrite() {
		return "{" +
			"\"message\":" + "\"" + message + "\"" +
			"}";
	}
}
