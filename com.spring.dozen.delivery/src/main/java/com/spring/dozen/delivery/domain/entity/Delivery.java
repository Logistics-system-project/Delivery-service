package com.spring.dozen.delivery.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.spring.dozen.delivery.domain.enums.DeliveryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "p_delivery")
public class Delivery extends BaseEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "delivery_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private UUID orderId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	@Column(nullable = false)
	private UUID departureHubId;

	@Column(nullable = false)
	private UUID arrivalHubId;

	@Column(nullable = false)
	private String address;

	@Column(length = 10, nullable = false)
	private String recipientName;

	@Column(length = 30, nullable = false)
	private String recipientSlackId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_delivery_staff_id", nullable = false)
	private DeliveryStaff companyDeliveryStaff;

	public static Delivery create(UUID orderId, UUID departureHubId, UUID arrivalHubId, String address, String recipientName, String recipientSlackId, DeliveryStaff companyDeliveryStaff) {
		return Delivery.builder()
			.orderId(orderId)
			.status(DeliveryStatus.HUB_WAITING)
			.departureHubId(departureHubId)
			.arrivalHubId(arrivalHubId)
			.address(address)
			.recipientName(recipientName)
			.recipientSlackId(recipientSlackId)
			.companyDeliveryStaff(companyDeliveryStaff)
			.build();
	}

	public void update(UUID departureHubId, UUID arrivalHubId, String address, String recipientName, String recipientSlackId, DeliveryStaff companyDeliveryStaff) {
		this.departureHubId = departureHubId;
		this.arrivalHubId = arrivalHubId;
		this.address = address;
		this.recipientName = recipientName;
		this.recipientSlackId = recipientSlackId;
		this.companyDeliveryStaff = companyDeliveryStaff;
	}

	public void updateStatus(DeliveryStatus status) {
		this.status = status;
	}
}
