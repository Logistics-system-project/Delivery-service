package com.spring.dozen.delivery.presentation.dto.deliveryHistory;

import java.util.UUID;

public record DeliveryHistorySearchCond(
	String status,
	UUID deliveryId,
	Long deliveryStaffId,
	UUID departureHubId,
	UUID arrivalHubId
) {
}
