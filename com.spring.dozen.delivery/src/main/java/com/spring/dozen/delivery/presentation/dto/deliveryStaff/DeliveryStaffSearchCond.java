package com.spring.dozen.delivery.presentation.dto.deliveryStaff;

public record DeliveryStaffSearchCond(
	String staffType,
	Long deliveryOrder
) {
}
