package com.spring.dozen.delivery.application.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryStatusUpdateResponse(
	UUID deliveryId,
	String status,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryStatusUpdateResponse from(Delivery delivery) {
		return new DeliveryStatusUpdateResponse(
			delivery.getId(),
			delivery.getStatus().toString(),
			delivery.getUpdatedAt().toString(),
			delivery.getUpdatedBy()
		);
	}
}
