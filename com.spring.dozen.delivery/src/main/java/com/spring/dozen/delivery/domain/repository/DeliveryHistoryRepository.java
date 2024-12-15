package com.spring.dozen.delivery.domain.repository;

import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;

@Repository
public interface DeliveryHistoryRepository {
	DeliveryHistory save(DeliveryHistory deliveryHistory);
	boolean existsByDeliveryAndDeliveryStaff(Delivery delivery, DeliveryStaff deliveryStaff);
}
