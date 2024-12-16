package com.spring.dozen.delivery.presentation.dto.delivery;

import com.spring.dozen.delivery.application.dto.delivery.DeliveryStatusUpdate;

import jakarta.validation.constraints.NotBlank;

public record DeliveryStatusUpdateRequest(
	@NotBlank
	String status
) {
	public DeliveryStatusUpdate toServiceDto(){
		return new DeliveryStatusUpdate(status);
	}
}
