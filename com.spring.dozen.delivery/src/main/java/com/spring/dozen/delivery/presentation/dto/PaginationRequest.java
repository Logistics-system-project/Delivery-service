package com.spring.dozen.delivery.presentation.dto;

public record PaginationRequest(
	Integer page,
	Integer size,
	String sortedBy,
	Boolean isAsc
) {
}