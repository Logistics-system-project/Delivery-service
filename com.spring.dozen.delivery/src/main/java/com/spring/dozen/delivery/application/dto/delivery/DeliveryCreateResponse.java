package com.spring.dozen.delivery.application.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryCreateResponse(
	UUID deliveryId,
	UUID orderId,
	String status,
	UUID departureHubId,
	UUID arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	Long companyDeliveryStaffId,
	String createdAt,
	String createdBy
) {
	public static DeliveryCreateResponse from(Delivery delivery) {
		return new DeliveryCreateResponse(
			delivery.getId(),
			delivery.getOrderId(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId(),
			delivery.getArrivalHubId(),
			delivery.getAddress(),
			delivery.getRecipientName(),
			delivery.getRecipientSlackId(),
			delivery.getCompanyDeliveryStaff().getId(),
			delivery.getCreatedAt().toString(),
			delivery.getCreatedBy()
		);
	}
}
