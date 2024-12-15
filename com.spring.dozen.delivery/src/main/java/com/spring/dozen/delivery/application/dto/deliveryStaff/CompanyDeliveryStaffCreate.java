package com.spring.dozen.delivery.application.dto.deliveryStaff;

import java.util.UUID;

public record CompanyDeliveryStaffCreate(
	Long deliveryStaffId,
	UUID hubId
) {
}
