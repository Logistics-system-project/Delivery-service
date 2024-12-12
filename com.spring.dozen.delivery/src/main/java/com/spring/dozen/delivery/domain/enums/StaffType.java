package com.spring.dozen.delivery.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StaffType {
	HUB_STAFF("허브 배송 담당자"),
	COMPANY_STAFF("업체 배송 담당자");

	private final String description;

	public static StaffType of(String request){
		return switch (request){
			case "HUB_STAFF" -> HUB_STAFF;
			case "COMPANY_STAFF" -> COMPANY_STAFF;
			default -> null;
		};
	}
}
