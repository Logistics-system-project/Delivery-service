package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

public record DeliveryHistoryDetailResponse(
	UUID deliveryHistoryId,
	UUID deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration,
	Double actualDistance,
	Integer actualDuration,
	String status,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryHistoryDetailResponse from(DeliveryHistory deliveryHistory) {
		return new DeliveryHistoryDetailResponse(
			deliveryHistory.getId(),
			deliveryHistory.getDelivery().getId(),
			deliveryHistory.getDeliveryStaff().getId(),
			deliveryHistory.getSequence(),
			deliveryHistory.getDepartureHubId(),
			deliveryHistory.getArrivalHubId(),
			deliveryHistory.getEstimatedDistance(),
			deliveryHistory.getEstimatedDuration(),
			deliveryHistory.getActualDistance(),
			deliveryHistory.getActualDuration(),
			deliveryHistory.getStatus().toString(),
			deliveryHistory.getUpdatedAt().toString(),
			deliveryHistory.getUpdatedBy()
		);
	}
}
