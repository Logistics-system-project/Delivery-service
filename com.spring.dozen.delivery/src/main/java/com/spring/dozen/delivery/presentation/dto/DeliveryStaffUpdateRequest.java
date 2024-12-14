package com.spring.dozen.delivery.presentation.dto;

import com.spring.dozen.delivery.application.dto.DeliveryStaffUpdate;

import jakarta.validation.constraints.NotBlank;

public record DeliveryStaffUpdateRequest(
	String hubId,
	@NotBlank
	String staffType
) {
	public DeliveryStaffUpdate toServiceDto(){
		return new DeliveryStaffUpdate(hubId, staffType);
	}
}
