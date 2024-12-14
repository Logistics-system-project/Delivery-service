package com.spring.dozen.delivery.presentation.dto.delivery;

public record DeliverySearchCond(
	String status,
	String departureHubId,
	String arrivalHubId
) {
}
