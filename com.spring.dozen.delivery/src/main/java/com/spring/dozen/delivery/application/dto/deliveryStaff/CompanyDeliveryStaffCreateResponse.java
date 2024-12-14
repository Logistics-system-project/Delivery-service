package com.spring.dozen.delivery.application.dto.deliveryStaff;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;

public record CompanyDeliveryStaffCreateResponse(
	Long deliveryStaffId,
	String hubId,
	Long deliveryOrder,
	String createdAt,
	String createdBy
) {
	public static CompanyDeliveryStaffCreateResponse from(DeliveryStaff deliveryStaff, DeliveryStaffHub deliveryStaffHub) {
		return new CompanyDeliveryStaffCreateResponse(
			deliveryStaff.getId(),
			deliveryStaffHub.getHubId().toString(),
			deliveryStaff.getDeliveryOrder(),
			deliveryStaff.getCreatedAt().toString(),
			deliveryStaff.getCreatedBy()
		);
	}
}
