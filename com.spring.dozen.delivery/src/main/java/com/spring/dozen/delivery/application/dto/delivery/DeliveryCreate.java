package com.spring.dozen.delivery.application.dto.delivery;

public record DeliveryCreate(
	String orderId,
	String departureHubId,
	String arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId
) {
}
