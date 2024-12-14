package com.spring.dozen.delivery.domain.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.spring.dozen.delivery.domain.enums.DeliveryHistoryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "p_delivery_history")
public class DeliveryHistory extends BaseEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "delivery_history_id", nullable = false, columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id", nullable = false)
	private Delivery delivery;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_staff_id", nullable = false)
	private DeliveryStaff deliveryStaff;

	@Column(nullable = false)
	private Integer sequence;

	@Column(nullable = false)
	private UUID departureHubId;

	@Column(nullable = false)
	private UUID arrivalHubId;

	@Column(columnDefinition = "DECIMAL(10,2)", nullable = false)
	private Double estimatedDistance;

	@Column(nullable = false)
	private Integer estimatedDuration;

	@Column(columnDefinition = "DECIMAL(10,2)")
	private Double actualDistance;

	@Column
	private Integer actualDuration;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DeliveryHistoryStatus status;

	public static DeliveryHistory create(Delivery delivery, DeliveryStaff deliveryStaff, UUID departureHubId, UUID arrivalHubId, Integer sequence, Double estimatedDistance, Integer estimatedDuration) {
		return DeliveryHistory.builder()
			.delivery(delivery)
			.deliveryStaff(deliveryStaff)
			.sequence(sequence)
			.departureHubId(departureHubId)
			.arrivalHubId(arrivalHubId)
			.estimatedDistance(estimatedDistance)
			.estimatedDuration(estimatedDuration)
			.status(DeliveryHistoryStatus.HUB_WAITING)
			.build();
	}
}
