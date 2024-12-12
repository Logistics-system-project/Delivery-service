package com.spring.dozen.delivery.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.StaffType;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;

public interface DeliveryStaffRepositoryImpl extends JpaRepository<DeliveryStaff, Long>, DeliveryStaffRepository {
	DeliveryStaff findTopByStaffTypeOrderByCreatedAtDesc(StaffType staffType);
}
