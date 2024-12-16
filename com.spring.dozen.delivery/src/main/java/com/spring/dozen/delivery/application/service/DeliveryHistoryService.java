package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreate;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreateResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryDetailResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryListResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryStatusUpdate;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryStatusUpdateResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryUpdate;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.DeliveryHistoryStatus;
import com.spring.dozen.delivery.domain.enums.Role;
import com.spring.dozen.delivery.domain.repository.DeliveryHistoryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryHistoryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryStaffRepository deliveryStaffRepository;
	private final DeliveryHistoryRepository deliveryHistoryRepository;

	@Transactional
	public DeliveryHistoryCreateResponse createDeliveryHistory(DeliveryHistoryCreate request) {

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

	public Page<DeliveryHistoryListResponse> getDeliveryHistoryList(Pageable pageable, DeliveryHistorySearchCond cond) {
		Page<DeliveryHistory> deliveryHistoryPage = deliveryHistoryRepository.findAllDeliveryHistoryByCond(pageable,
			cond);
		return deliveryHistoryPage.map(DeliveryHistoryListResponse::from);
	}

	public DeliveryHistoryDetailResponse getDeliveryHistoryDetail(UUID deliveryHistoryId, String userId, String role) {
		DeliveryHistory deliveryHistory = findDeliveryHistoryById(deliveryHistoryId);
		roleCheck(deliveryHistory, userId, role);
		return DeliveryHistoryDetailResponse.from(deliveryHistory);
	}

	@Transactional
	public DeliveryHistoryDetailResponse updateDeliveryHistory(UUID deliveryHistoryId, DeliveryHistoryUpdate request, String userId, String role) {
		DeliveryHistory deliveryHistory = findDeliveryHistoryById(deliveryHistoryId);

		// 허브 관리자일 때, 담당 허브인지 확인하는 로직 필요

		roleCheck(deliveryHistory, userId, role);

		DeliveryStaff deliveryStaff = findDeliveryStaffById(request.deliveryStaffId());

		deliveryHistory.update(
			deliveryStaff,
			request.sequence(),
			request.departureHubId(),
			request.arrivalHubId(),
			request.estimatedDistance(),
			request.estimatedDuration()
		);
		return DeliveryHistoryDetailResponse.from(deliveryHistory);
	}

	@Transactional
	public DeliveryHistoryStatusUpdateResponse updateDeliveryHistoryStatus(UUID deliveryHistoryId, DeliveryHistoryStatusUpdate request, String userId, String role) {
		DeliveryHistory deliveryHistory = findDeliveryHistoryById(deliveryHistoryId);

		roleCheck(deliveryHistory, userId, role);

		DeliveryHistoryStatus deliveryHistoryStatus =getDeliveryHistoryStatus(request.status());

		deliveryHistory.updateStatus(deliveryHistoryStatus);

		return DeliveryHistoryStatusUpdateResponse.from(deliveryHistory);
	}

	@Transactional
	public void deleteDeliveryHistory(UUID deliveryHistoryId, String userId, String role) {
		DeliveryHistory deliveryHistory = findDeliveryHistoryById(deliveryHistoryId);

		// 허브 관리자일 때, 담당 허브인지 확인하는 로직 필요

		deliveryHistory.deleteBase(Long.valueOf(userId));
	}

	/* UTIL */

	private Delivery findDeliveryById(UUID deliveryId) {
		return deliveryRepository.findById(deliveryId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_NOT_FOUND));
	}

	private DeliveryStaff findDeliveryStaffById(Long deliveryStaffId) {
		return deliveryStaffRepository.findById(deliveryStaffId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_STAFF_NOT_FOUND));
	}

	private DeliveryHistory findDeliveryHistoryById(UUID deliveryHistoryId) {
		return deliveryHistoryRepository.findById(deliveryHistoryId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_HISTORY_NOT_FOUND));
	}

	private DeliveryHistoryStatus getDeliveryHistoryStatus(String status) {
		DeliveryHistoryStatus deliveryHistoryStatus = DeliveryHistoryStatus.of(status);
		if(deliveryHistoryStatus==null)
			throw new DeliveryException(ErrorCode.UNSUPPORTED_DELIVERY_HISTORY_STATUS);
		return deliveryHistoryStatus;
	}

	private void roleCheck(DeliveryHistory deliveryHistory, String userId, String role) {
		if (Role.of(role).isSame(Role.HUB_DELIVERY_STAFF)) {
			DeliveryStaff deliveryStaff = findDeliveryStaffById(Long.parseLong(userId));
			if (!deliveryHistory.getDeliveryStaff().getId().equals(deliveryStaff.getId()))
				throw new DeliveryException(ErrorCode.ACCESS_DENIED);
		}
	}
}

