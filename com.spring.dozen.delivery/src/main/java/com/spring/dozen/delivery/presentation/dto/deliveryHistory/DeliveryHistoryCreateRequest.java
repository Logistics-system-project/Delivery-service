package com.spring.dozen.delivery.presentation.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreate;

import jakarta.validation.constraints.NotNull;

public record DeliveryHistoryCreateRequest(
	@NotNull
	UUID deliveryId,

	@NotNull
	Long deliveryStaffId,

	@NotNull
	Integer sequence,

	@NotNull
	UUID departureHubId,

	@NotNull
	UUID arrivalHubId,

	@NotNull
	Double estimatedDistance,

	@NotNull
	Integer estimatedDuration
) {
	public DeliveryHistoryCreate toServiceDto(){
		return new DeliveryHistoryCreate(deliveryId, deliveryStaffId, sequence, departureHubId, arrivalHubId, estimatedDistance, estimatedDuration);
	}
}
