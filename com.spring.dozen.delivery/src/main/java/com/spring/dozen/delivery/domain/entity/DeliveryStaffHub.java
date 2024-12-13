package com.spring.dozen.delivery.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.spring.dozen.delivery.domain.enums.StaffType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "p_delivery_staff_hub")
public class DeliveryStaffHub extends BaseEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "delivery_staff_hub_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "delivery_staff_id", nullable = false, unique = true)
	private DeliveryStaff deliveryStaff;

	@Column(nullable = false)
	private UUID hubId;

	public static DeliveryStaffHub create(DeliveryStaff deliveryStaff, UUID hubId) {
		return DeliveryStaffHub.builder()
			.deliveryStaff(deliveryStaff)
			.hubId(hubId)
			.build();
	}
}
