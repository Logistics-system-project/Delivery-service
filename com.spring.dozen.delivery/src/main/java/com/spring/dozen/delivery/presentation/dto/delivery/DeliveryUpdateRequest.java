package com.spring.dozen.delivery.presentation.dto.delivery;

import java.util.UUID;

import com.spring.dozen.delivery.application.dto.delivery.DeliveryUpdate;

public record DeliveryUpdateRequest(
	UUID departureHubId,
	UUID arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	Long companyDeliveryStaffId
) {
	public DeliveryUpdate toServiceDto(){
		return new DeliveryUpdate(departureHubId, arrivalHubId, address, recipientName, recipientSlackId, companyDeliveryStaffId);
	}
}
