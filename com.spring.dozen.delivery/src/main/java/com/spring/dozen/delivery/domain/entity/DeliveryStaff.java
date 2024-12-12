package com.spring.dozen.delivery.domain.entity;

import java.util.UUID;

import com.spring.dozen.delivery.domain.enums.StaffType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_delivery_staff")
public class DeliveryStaff extends BaseEntity {
	@Id
	@Column(name = "delivery_staff_id")
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StaffType staffType;

	@Column(nullable = false)
	private Long deliveryOrder;

	public static DeliveryStaff create(Long id, StaffType staffType, Long deliveryOrder) {
		return DeliveryStaff.builder()
			.id(id)
			.staffType(staffType)
			.deliveryOrder(deliveryOrder)
			.build();
	}
}
