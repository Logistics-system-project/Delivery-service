package com.spring.dozen.delivery.presentation.dto.deliveryHistory;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryStatusUpdate;

import jakarta.validation.constraints.NotBlank;

public record DeliveryHistoryStatusUpdateRequest(
	@NotBlank
	String status
) {
	public DeliveryHistoryStatusUpdate toServiceDto(){
		return new DeliveryHistoryStatusUpdate(status);
	}
}
