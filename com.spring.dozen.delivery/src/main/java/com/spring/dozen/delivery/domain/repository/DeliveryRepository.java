package com.spring.dozen.delivery.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.spring.dozen.delivery.domain.entity.Delivery;

@Repository
public interface DeliveryRepository {
	Delivery save(Delivery delivery);
	Optional<Delivery> findById(UUID id);
}
