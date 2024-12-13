package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.StaffType;

@Repository
public interface DeliveryStaffRepository {
	boolean existsById(Long id);
	DeliveryStaff findTopByStaffTypeOrderByCreatedAtDesc(StaffType staffType);
	DeliveryStaff save(DeliveryStaff deliveryStaff);
}
