package com.spring.dozen.delivery.application.dto.deliveryStaff;

import java.util.UUID;

public record DeliveryStaffUpdate(
	UUID hubId,
	String staffType
) {
}