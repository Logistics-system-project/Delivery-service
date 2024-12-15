package com.spring.dozen.delivery.presentation.dto;

import com.spring.dozen.delivery.application.dto.DeliveryHistoryCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeliveryHistoryCreateRequest(
	@NotBlank
	String deliveryId,

	@NotNull
	Long deliveryStaffId,

	@NotNull
	Integer sequence,

	@NotBlank
	String departureHubId,

	@NotBlank
	String arrivalHubId,

	@NotNull
	Double estimatedDistance,

	@NotNull
	Integer estimatedDuration
) {
	public DeliveryHistoryCreate toServiceDto(){
		return new DeliveryHistoryCreate(deliveryId, deliveryStaffId, sequence, departureHubId, arrivalHubId, estimatedDistance, estimatedDuration);
	}
}
