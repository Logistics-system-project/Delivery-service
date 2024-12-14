package com.spring.dozen.delivery.application.dto.deliveryStaff;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

public record DeliveryStaffDetailResponse (
	Long deliveryStaffId,
	String hubId,
	String staffType,
	Long deliveryOrder,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryStaffDetailResponse from(DeliveryStaff deliveryStaff, String hubId) {
		return new DeliveryStaffDetailResponse(
			deliveryStaff.getId(),
			hubId,
			deliveryStaff.getStaffType().toString(),
			deliveryStaff.getDeliveryOrder(),
			deliveryStaff.getUpdatedAt().toString(),
			deliveryStaff.getUpdatedBy()
		);
	}
}