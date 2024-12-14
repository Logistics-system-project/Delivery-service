package com.spring.dozen.delivery.application.dto.delivery;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryDetailResponse(
	String deliveryId,
	String orderId,
	String status,
	String departureHubId,
	String arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryDetailResponse from(Delivery delivery) {
		return new DeliveryDetailResponse(
			delivery.getId().toString(),
			delivery.getOrderId().toString(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId().toString(),
			delivery.getArrivalHubId().toString(),
			delivery.getAddress(),
			delivery.getRecipientName(),
			delivery.getRecipientSlackId(),
			delivery.getUpdatedAt().toString(),
			delivery.getUpdatedBy()
		);
	}
}
