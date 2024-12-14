package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.DeliveryCreateResponse;
import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.repository.DeliveryRepository;
import com.spring.dozen.delivery.presentation.dto.DeliveryCreateRequest;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	@Transactional
	public DeliveryCreateResponse createDelivery(DeliveryCreateRequest request) {

		// 존재하는 허브인지 확인하는 로직 구현 필요
		Delivery delivery = Delivery.create(
			UUID.fromString(request.orderId()),
			UUID.fromString(request.departureHubId()),
			UUID.fromString(request.arrivalHubId()),
			request.address(),
			request.recipientName(),
			request.recipientSlackId()
		);
		return DeliveryCreateResponse.from(deliveryRepository.save(delivery));
	}
}
