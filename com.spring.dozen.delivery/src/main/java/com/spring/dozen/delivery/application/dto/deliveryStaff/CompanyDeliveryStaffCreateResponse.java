package com.spring.dozen.delivery.application.dto.deliveryStaff;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;

public record CompanyDeliveryStaffCreateResponse(
	Long deliveryStaffId,
	UUID hubId,
	Long deliveryOrder,
	String createdAt,
	String createdBy
) {
	public static CompanyDeliveryStaffCreateResponse from(DeliveryStaff deliveryStaff, DeliveryStaffHub deliveryStaffHub) {
		return new CompanyDeliveryStaffCreateResponse(
			deliveryStaff.getId(),
			deliveryStaffHub.getHubId(),
			deliveryStaff.getDeliveryOrder(),
			deliveryStaff.getCreatedAt().toString(),
			deliveryStaff.getCreatedBy()
		);
	}
}
