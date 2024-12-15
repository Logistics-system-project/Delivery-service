package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

public record DeliveryHistoryCreate(
	UUID deliveryId,
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration
) {
}
