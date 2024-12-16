package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;

@Repository
public interface DeliveryHistoryRepository {
	DeliveryHistory save(DeliveryHistory deliveryHistory);
	boolean existsByDeliveryAndDeliveryStaff(Delivery delivery, DeliveryStaff deliveryStaff);
	Page<DeliveryHistory> findAllDeliveryHistoryByCond(Pageable pageable, DeliveryHistorySearchCond cond);
	Optional<DeliveryHistory> findById(UUID id);
}
