package com.spring.dozen.delivery.application.dto.deliveryStaff;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

public record DeliveryStaffDetailResponse (
	Long deliveryStaffId,
	UUID hubId,
	String staffType,
	Long deliveryOrder,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryStaffDetailResponse from(DeliveryStaff deliveryStaff, UUID hubId) {
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