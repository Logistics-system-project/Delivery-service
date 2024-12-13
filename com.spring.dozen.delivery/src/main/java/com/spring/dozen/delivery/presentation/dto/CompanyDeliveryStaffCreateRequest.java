package com.spring.dozen.delivery.presentation.dto;

import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreate;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreate;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.enums.StaffType;

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
