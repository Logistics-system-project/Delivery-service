package com.spring.dozen.delivery.application.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryListResponse(
	UUID deliveryId,
	UUID orderId,
	String status,
	UUID departureHubId,
	UUID arrivalHubId,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryListResponse from(Delivery delivery) {
		return new DeliveryListResponse(
			delivery.getId(),
			delivery.getOrderId(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId(),
			delivery.getArrivalHubId(),
			delivery.getUpdatedAt().toString(),
			delivery.getUpdatedBy()
		);
	}
}
