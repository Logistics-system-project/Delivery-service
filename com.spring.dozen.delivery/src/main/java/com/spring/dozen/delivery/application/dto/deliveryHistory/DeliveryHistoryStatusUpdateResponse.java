package com.spring.dozen.delivery.application.dto.deliveryHistory;

import java.util.UUID;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

public record DeliveryHistoryStatusUpdateResponse(
	UUID deliveryHistoryId,
	String status,
	String updatedAt,
	String updatedBy
) {
	public static DeliveryHistoryStatusUpdateResponse from(DeliveryHistory deliveryHistory) {
		return new DeliveryHistoryStatusUpdateResponse(
			deliveryHistory.getId(),
			deliveryHistory.getStatus().toString(),
			deliveryHistory.getUpdatedAt().toString(),
			deliveryHistory.getUpdatedBy()
		);
	}
}
