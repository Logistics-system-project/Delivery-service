package com.spring.dozen.delivery.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryHistoryStatus {
	HUB_WAITING("허브 대기중"),
	HUB_IN_TRANSIT("허브 이동중"),
	HUB_ARRIVED("목적지 허브 도착"),
	OUT_FOR_DELIVERY("배송중");

	private final String description;

	public static DeliveryHistoryStatus of(String request) {
		return switch (request){
			case "HUB_WAITING" -> HUB_WAITING;
			case "HUB_IN_TRANSIT" -> HUB_IN_TRANSIT;
			case "HUB_ARRIVED" -> HUB_ARRIVED;
			case "OUT_FOR_DELIVERY" -> OUT_FOR_DELIVERY;
			default -> null;
		};
	}
}
