package com.spring.dozen.delivery.application.dto.delivery;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryResponse(
	String deliveryId,
	String orderId,
	String status,
	String departureHubId,
	String arrivalHubId,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryResponse from(Delivery delivery) {
		return new DeliveryResponse(
			delivery.getId().toString(),
			delivery.getOrderId().toString(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId().toString(),
			delivery.getArrivalHubId().toString(),
			delivery.getUpdatedAt().toString(),
			delivery.getUpdatedBy()
		);
	}
}
