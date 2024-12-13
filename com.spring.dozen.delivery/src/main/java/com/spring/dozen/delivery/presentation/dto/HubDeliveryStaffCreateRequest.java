package com.spring.dozen.delivery.presentation.dto;

import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreate;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.enums.StaffType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HubDeliveryStaffCreateRequest(
	@NotNull
	Long deliveryStaffId
) {
	public HubDeliveryStaffCreate toServiceDto() {
		return new HubDeliveryStaffCreate(deliveryStaffId);
	}
}
