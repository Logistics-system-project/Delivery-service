package com.spring.dozen.delivery.application.dto.delivery;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryCreateResponse(
	String deliveryId,
	String orderId,
	String status,
	String departureHubId,
	String arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	String createdAt,
	String createdBy
) {
	public static DeliveryCreateResponse from(Delivery delivery) {
		return new DeliveryCreateResponse(
			delivery.getId().toString(),
			delivery.getOrderId().toString(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId().toString(),
			delivery.getArrivalHubId().toString(),
			delivery.getAddress(),
			delivery.getRecipientName(),
			delivery.getRecipientSlackId(),
			delivery.getCreatedAt().toString(),
			delivery.getCreatedBy()
		);
	}
}
