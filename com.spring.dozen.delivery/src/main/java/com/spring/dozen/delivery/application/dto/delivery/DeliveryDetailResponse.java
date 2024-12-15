package com.spring.dozen.delivery.application.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.Delivery;

public record DeliveryDetailResponse(
	UUID deliveryId,
	UUID orderId,
	String status,
	UUID departureHubId,
	UUID arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	Long companyDeliveryStaffId,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryDetailResponse from(Delivery delivery) {
		return new DeliveryDetailResponse(
			delivery.getId(),
			delivery.getOrderId(),
			delivery.getStatus().toString(),
			delivery.getDepartureHubId(),
			delivery.getArrivalHubId(),
			delivery.getAddress(),
			delivery.getRecipientName(),
			delivery.getRecipientSlackId(),
			delivery.getCompanyDeliveryStaff().getId(),
			delivery.getUpdatedAt().toString(),
			delivery.getUpdatedBy()
		);
	}
}
