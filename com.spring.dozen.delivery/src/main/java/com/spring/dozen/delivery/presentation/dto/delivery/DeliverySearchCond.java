package com.spring.dozen.delivery.presentation.dto.delivery;

import java.util.UUID;

public record DeliverySearchCond(
	String status,
	UUID departureHubId,
	UUID arrivalHubId
) {
}
