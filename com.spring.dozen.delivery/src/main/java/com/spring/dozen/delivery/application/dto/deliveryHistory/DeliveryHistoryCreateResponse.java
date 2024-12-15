package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

public record DeliveryHistoryCreateResponse(
	UUID deliveryHistoryId,
	UUID deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration,
	String status,
	String createdAt,
	String createdBy
) {
	public static DeliveryHistoryCreateResponse from(DeliveryHistory deliveryHistory) {
		return new DeliveryHistoryCreateResponse(
			deliveryHistory.getId(),
			deliveryHistory.getDelivery().getId(),
			deliveryHistory.getDeliveryStaff().getId(),
			deliveryHistory.getSequence(),
			deliveryHistory.getDepartureHubId(),
			deliveryHistory.getArrivalHubId(),
			deliveryHistory.getEstimatedDistance(),
			deliveryHistory.getEstimatedDuration(),
			deliveryHistory.getStatus().toString(),
			deliveryHistory.getCreatedAt().toString(),
			deliveryHistory.getCreatedBy()
		);
	}
}
