package com.spring.dozen.delivery.application.dto.deliveryStaff;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

public record HubDeliveryStaffCreateResponse(
	Long deliveryStaffId,
	Long deliveryOrder,
	String createdAt,
	String createdBy
) {
	public static HubDeliveryStaffCreateResponse from(DeliveryStaff deliveryStaff) {
		return new HubDeliveryStaffCreateResponse(
			deliveryStaff.getId(),
			deliveryStaff.getDeliveryOrder(),
			deliveryStaff.getCreatedAt().toString(),
			deliveryStaff.getCreatedBy()
		);
	}
}
