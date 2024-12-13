package com.spring.dozen.delivery.presentation.dto;

public record DeliveryStaffSearchCond(
	String staffType,
	Long deliveryOrder
) {
}
