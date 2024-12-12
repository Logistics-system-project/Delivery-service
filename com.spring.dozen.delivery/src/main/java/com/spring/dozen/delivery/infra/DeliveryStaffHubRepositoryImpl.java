package com.spring.dozen.delivery.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffHubRepository;

public interface DeliveryStaffHubRepositoryImpl extends JpaRepository<DeliveryStaffHub, UUID>,
	DeliveryStaffHubRepository {
}