package com.spring.dozen.delivery.domain.repository;

import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;

@Repository
public interface DeliveryHistoryRepository {
	DeliveryHistory save(DeliveryHistory deliveryHistory);
}
