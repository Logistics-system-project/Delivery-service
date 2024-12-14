package com.spring.dozen.delivery.presentation.dto;

import com.spring.dozen.delivery.application.dto.DeliveryCreate;

import jakarta.validation.constraints.NotBlank;

public record DeliveryCreateRequest(
	@NotBlank
	String orderId,

	@NotBlank
	String departureHubId,

	@NotBlank
	String arrivalHubId,

	@NotBlank
	String address,

	@NotBlank
	String recipientName,

	@NotBlank
	String recipientSlackId
	) {
	public DeliveryCreate toServiceDto(){
		return new DeliveryCreate(orderId, departureHubId, arrivalHubId, address, recipientName, recipientSlackId);
	}
}
