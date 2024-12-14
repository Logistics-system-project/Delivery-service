package com.spring.dozen.delivery.application.dto;

public record DeliveryHistoryCreate(
	String deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	String departureHubId,
	String arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration
) {
}
