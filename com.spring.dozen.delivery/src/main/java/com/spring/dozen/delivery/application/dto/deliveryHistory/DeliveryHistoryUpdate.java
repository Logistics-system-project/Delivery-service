package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

public record DeliveryHistoryUpdate(
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration
) {
}
