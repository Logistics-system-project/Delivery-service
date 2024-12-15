package com.spring.dozen.delivery.presentation.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.application.dto.delivery.DeliveryCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeliveryCreateRequest(
	@NotNull
	UUID orderId,

	@NotNull
	UUID departureHubId,

	@NotNull
	UUID arrivalHubId,

	@NotBlank
	String address,

	@NotBlank
	String recipientName,

	@NotBlank
	String recipientSlackId,

	@NotNull
	Long companyDeliveryStaffId
	) {
	public DeliveryCreate toServiceDto(){
		return new DeliveryCreate(orderId, departureHubId, arrivalHubId, address, recipientName, recipientSlackId, companyDeliveryStaffId);
	}
}
