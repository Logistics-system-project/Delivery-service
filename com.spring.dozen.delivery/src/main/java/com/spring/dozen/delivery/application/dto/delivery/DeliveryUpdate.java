package com.spring.dozen.delivery.application.dto.delivery;

import java.util.UUID;

public record DeliveryUpdate(
	UUID departureHubId,
	UUID arrivalHubId,
	String address,
	String recipientName,
	String recipientSlackId,
	Long companyDeliveryStaffId
) {
}
