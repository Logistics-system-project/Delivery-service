package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;

@Repository
public interface DeliveryRepository {
	Delivery save(Delivery delivery);

	Optional<Delivery> findById(UUID id);

	Page<Delivery> findDeliveries(DeliverySearchCond cond, Pageable pageable);
}
