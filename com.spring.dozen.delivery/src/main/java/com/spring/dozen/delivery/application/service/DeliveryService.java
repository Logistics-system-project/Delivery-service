package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.delivery.DeliveryCreateResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryDetailResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryListResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryStatusUpdateResponse;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.DeliveryStatus;
import com.spring.dozen.delivery.domain.enums.Role;
import com.spring.dozen.delivery.domain.repository.DeliveryHistoryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryCreateRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryStatusUpdateRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryUpdateRequest;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryHistoryRepository deliveryHistoryRepository;
	private final DeliveryStaffRepository deliveryStaffRepository;

	@Transactional
	public DeliveryCreateResponse createDelivery(DeliveryCreateRequest request) {

		DeliveryStaff deliveryStaff = findDeliveryStaffById(request.companyDeliveryStaffId());

		// 존재하는 허브인지 확인하는 로직 구현 필요
		Delivery delivery = Delivery.create(
			request.orderId(),
			request.departureHubId(),
			request.arrivalHubId(),
			request.address(),
			request.recipientName(),
			request.recipientSlackId(),
			deliveryStaff
		);
		return DeliveryCreateResponse.from(deliveryRepository.save(delivery));
	}

	public Page<DeliveryListResponse> getDeliveryList(Pageable pageable, DeliverySearchCond cond) {
		Page<Delivery> deliveryPage = deliveryRepository.findDeliveries(cond, pageable);
		return deliveryPage.map(DeliveryListResponse::from);
	}

	public DeliveryDetailResponse getDeliveryDetail(UUID deliveryId, String userId, String role) {
		Delivery delivery = findDeliveryById(deliveryId);

		roleCheck(delivery, userId, role);

		return DeliveryDetailResponse.from(delivery);
	}

	@Transactional
	public DeliveryDetailResponse updateDelivery(UUID deliveryId, DeliveryUpdateRequest request, String userId, String role) {
		Delivery delivery = findDeliveryById(deliveryId);

		// 허브 관리자일 때, 담당 허브인지 확인하는 로직 필요

		roleCheck(delivery, userId, role);

		DeliveryStaff deliveryStaff = findDeliveryStaffById(request.companyDeliveryStaffId());

		delivery.update(
			request.departureHubId(),
			request.arrivalHubId(),
			request.address(),
			request.recipientName(),
			request.recipientSlackId(),
			deliveryStaff
		);
		return DeliveryDetailResponse.from(delivery);
	}

	@Transactional
	public DeliveryStatusUpdateResponse updateDeliveryStatus(UUID deliveryId, DeliveryStatusUpdateRequest request, String userId, String role) {
		Delivery delivery = findDeliveryById(deliveryId);

		// 허브 관리자일 때, 담당 허브인지 확인하는 로직 필요

		roleCheck(delivery, userId, role);

		DeliveryStatus deliveryStatus = getDeliveryStatus(request.status());

		delivery.updateStatus(deliveryStatus);
		return DeliveryStatusUpdateResponse.from(delivery);
	}

	/* UTIL */

	private Delivery findDeliveryById(UUID deliveryId) {
		return deliveryRepository.findById(deliveryId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_NOT_FOUND));
	}

	private DeliveryStaff findDeliveryStaffById(Long deliveryId) {
		return deliveryStaffRepository.findById(deliveryId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_STAFF_NOT_FOUND));
	}

	private DeliveryStatus getDeliveryStatus(String status) {
		DeliveryStatus deliveryStatus = DeliveryStatus.of(status);
		if(deliveryStatus==null) throw new DeliveryException(ErrorCode.UNSUPPORTED_DELIVERY_STATUS);
		return deliveryStatus;
	}

	private void roleCheck(Delivery delivery, String userId, String role) {
		if (Role.of(role).isSame(Role.HUB_DELIVERY_STAFF)) {
			DeliveryStaff deliveryStaff = findDeliveryStaffById(Long.valueOf(userId));
			if (!deliveryHistoryRepository.existsByDeliveryAndDeliveryStaff(delivery, deliveryStaff))
				throw new DeliveryException(ErrorCode.ACCESS_DENIED);
		}
	}
}
