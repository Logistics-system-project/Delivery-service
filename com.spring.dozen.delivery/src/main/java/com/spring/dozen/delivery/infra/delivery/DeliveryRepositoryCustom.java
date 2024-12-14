package com.spring.dozen.delivery.infra.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;

public interface DeliveryRepositoryCustom {
	Page<Delivery> findDeliveries(DeliverySearchCond cond, Pageable pageable);
}