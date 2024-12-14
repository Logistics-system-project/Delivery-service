package com.spring.dozen.delivery.presentation.dto.deliveryStaff;

import com.spring.dozen.delivery.application.dto.deliveryStaff.HubDeliveryStaffCreate;

import jakarta.validation.constraints.NotNull;

public record HubDeliveryStaffCreateRequest(
	@NotNull
	Long deliveryStaffId
) {
	public HubDeliveryStaffCreate toServiceDto() {
		return new HubDeliveryStaffCreate(deliveryStaffId);
	}
}
