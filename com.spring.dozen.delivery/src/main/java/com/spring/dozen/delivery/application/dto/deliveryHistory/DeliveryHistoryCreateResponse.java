package com.spring.dozen.delivery.application.dto.deliveryHistory;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

public record DeliveryHistoryCreateResponse(
	String deliveryHistoryId,
	String deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	String departureHubId,
	String arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration,
	String status,
	String createdAt,
	String createdBy
) {
	public static DeliveryHistoryCreateResponse from(DeliveryHistory deliveryHistory) {
		return new DeliveryHistoryCreateResponse(
			deliveryHistory.getId().toString(),
			deliveryHistory.getDelivery().getId().toString(),
			deliveryHistory.getDeliveryStaff().getId(),
			deliveryHistory.getSequence(),
			deliveryHistory.getDepartureHubId().toString(),
			deliveryHistory.getArrivalHubId().toString(),
			deliveryHistory.getEstimatedDistance(),
			deliveryHistory.getEstimatedDuration(),
			deliveryHistory.getStatus().toString(),
			deliveryHistory.getCreatedAt().toString(),
			deliveryHistory.getCreatedBy()
		);
	}
}
