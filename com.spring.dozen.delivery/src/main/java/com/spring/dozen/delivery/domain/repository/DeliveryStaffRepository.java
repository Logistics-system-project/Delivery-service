package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.StaffType;

@Repository
public interface DeliveryStaffRepository {
	boolean existsById(Long id);
	DeliveryStaff findTopByStaffTypeOrderByCreatedAtDesc(StaffType staffType);
	DeliveryStaff save(DeliveryStaff deliveryStaff);
	Page<DeliveryStaff> findByIsDeletedFalse(Pageable pageable);
	Page<DeliveryStaff> findByStaffType(StaffType staffType, Pageable pageable);
	Page<DeliveryStaff> findByDeliveryOrder(Long deliveryOrder, Pageable pageable);
}
