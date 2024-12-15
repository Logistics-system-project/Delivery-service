package com.spring.dozen.delivery.application.dto;

public record DeliveryCreate(
	String orderId,
	String departureHubId,
	String arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId
) {
}
