package com.spring.dozen.delivery.infra.deliveryStaff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.dozen.delivery.presentation.dto.deliveryStaff.DeliveryStaffSearchCond;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

public interface DeliveryStaffRepositoryCustom {
	Page<DeliveryStaff> findAllDeliveryStaffByStaffTypeAndDeliveryOrder(
		DeliveryStaffSearchCond cond, Pageable pageable);
}
