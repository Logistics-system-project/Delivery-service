package com.spring.dozen.delivery.infra.deliveryHistory;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.repository.DeliveryHistoryRepository;

public interface DeliveryHistoryRepositoryImpl extends JpaRepository<DeliveryHistory, UUID>, DeliveryHistoryRepository {
	boolean existsByDeliveryAndDeliveryStaff(Delivery delivery, DeliveryStaff deliveryStaff);
}
