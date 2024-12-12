package com.spring.dozen.delivery.infra;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.StaffType;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;

public interface DeliveryStaffRepositoryImpl extends JpaRepository<DeliveryStaff, Long>, DeliveryStaffRepository {
	DeliveryStaff findTopByStaffTypeOrderByCreatedAtDesc(StaffType staffType);
	Page<DeliveryStaff> findByIsDeletedFalse(Pageable pageable);
	Page<DeliveryStaff> findByStaffType(StaffType staffType, Pageable pageable);
	Page<DeliveryStaff> findByDeliveryOrder(Long deliveryOrder, Pageable pageable);
}
