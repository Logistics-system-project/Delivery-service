package com.spring.dozen.delivery.application.dto.deliveryStaff;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

public record DeliveryStaffListResponse(
	Long deliveryStaffId,
	String staffType,
	Long deliveryOrder,
	String updatedAt,
	String updatedBy
){
	public static DeliveryStaffListResponse from(DeliveryStaff deliveryStaff) {
		return new DeliveryStaffListResponse(
			deliveryStaff.getId(),
			deliveryStaff.getStaffType().toString(),
			deliveryStaff.getDeliveryOrder(),
			deliveryStaff.getUpdatedAt().toString(),
			deliveryStaff.getUpdatedBy()
		);
	}
}
