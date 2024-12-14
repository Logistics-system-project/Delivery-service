package com.spring.dozen.delivery.presentation.dto.deliveryStaff;

import com.spring.dozen.delivery.application.dto.deliveryStaff.CompanyDeliveryStaffCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompanyDeliveryStaffCreateRequest(
	@NotNull
	Long deliveryStaffId,
	@NotBlank
	String hubId
) {
	public CompanyDeliveryStaffCreate toServiceDto() {
		return new CompanyDeliveryStaffCreate(deliveryStaffId, hubId);
	}
}
