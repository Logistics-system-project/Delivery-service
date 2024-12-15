package com.spring.dozen.delivery.presentation.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryUpdate;

public record DeliveryHistoryUpdateRequest(
	Long deliveryStaffId,
	Integer sequence,
	UUID departureHubId,
	UUID arrivalHubId,
	Double estimatedDistance,
	Integer estimatedDuration
) {
	public DeliveryHistoryUpdate toServiceDto(){
		return new DeliveryHistoryUpdate(deliveryStaffId, sequence, departureHubId, arrivalHubId, estimatedDistance, estimatedDuration);
	}
}