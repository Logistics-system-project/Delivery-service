package com.spring.dozen.delivery.infra.delivery;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.repository.DeliveryRepository;

public interface DeliveryRepositoryImpl extends JpaRepository<Delivery, UUID>, DeliveryRepository, DeliveryRepositoryCustom {
}
