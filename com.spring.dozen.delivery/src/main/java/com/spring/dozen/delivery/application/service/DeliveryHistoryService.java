package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreateResponse;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.repository.DeliveryHistoryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryCreateRequest;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryHistoryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryStaffRepository deliveryStaffRepository;
	private final DeliveryHistoryRepository deliveryHistoryRepository;

	@Transactional
	public DeliveryHistoryCreateResponse createDeliveryHistory(DeliveryHistoryCreateRequest request) {

		Delivery delivery = findDeliveryById(request.deliveryId());
		DeliveryStaff deliveryStaff = findDeliveryStaffById(request.deliveryStaffId());

		DeliveryHistory deliveryHistory = DeliveryHistory.create(
			delivery,
			deliveryStaff,
			request.sequence(),
			request.departureHubId(),
			request.arrivalHubId(),
			request.estimatedDistance(),
			request.estimatedDuration()
		);

		return DeliveryHistoryCreateResponse.from(deliveryHistoryRepository.save(deliveryHistory));
	}

	private Delivery findDeliveryById(UUID deliveryId) {
		return deliveryRepository.findById(deliveryId).orElseThrow(()-> new DeliveryException(ErrorCode.DELIVERY_NOT_FOUND));
	}

	private DeliveryStaff findDeliveryStaffById(Long deliveryStaffId) {
		return deliveryStaffRepository.findById(deliveryStaffId).orElseThrow(()-> new DeliveryException(ErrorCode.DELIVERY_STAFF_NOT_FOUND));
	}
}
