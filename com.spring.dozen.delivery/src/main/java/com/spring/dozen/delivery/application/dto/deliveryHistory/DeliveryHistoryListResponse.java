package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

public record DeliveryHistoryListResponse(
	UUID deliveryHistoryId,
	UUID deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	String status,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryHistoryListResponse from(DeliveryHistory deliveryHistory) {
		return new DeliveryHistoryListResponse(
			deliveryHistory.getId(),
			deliveryHistory.getDelivery().getId(),
			deliveryHistory.getDeliveryStaff().getId(),
			deliveryHistory.getSequence(),
			deliveryHistory.getDepartureHubId(),
			deliveryHistory.getArrivalHubId(),
			deliveryHistory.getStatus().toString(),
			deliveryHistory.getUpdatedAt().toString(),
			deliveryHistory.getUpdatedBy()
		);
	}
}
