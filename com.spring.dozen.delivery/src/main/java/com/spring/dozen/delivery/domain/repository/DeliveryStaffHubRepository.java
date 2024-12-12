package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;

@Repository
public interface DeliveryStaffHubRepository {
	DeliveryStaffHub save(DeliveryStaffHub deliveryStaffHub);
	Optional<DeliveryStaffHub> findByDeliveryStaffId(Long deliveryStaffId);
}
