package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreate;
import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.dto.DeliveryStaffDetailResponse;
import com.spring.dozen.delivery.application.dto.DeliveryStaffListResponse;
import com.spring.dozen.delivery.presentation.dto.DeliveryStaffSearchCond;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreate;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;
import com.spring.dozen.delivery.domain.enums.StaffType;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffHubRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;
import com.spring.dozen.delivery.presentation.dto.DeliveryStaffUpdateRequest;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DeliveryStaffService {

	private final DeliveryStaffRepository deliveryStaffRepository;
	private final DeliveryStaffHubRepository deliveryStaffHubRepository;

	@Transactional
	public HubDeliveryStaffCreateResponse createHubDeliveryStaff(HubDeliveryStaffCreate requestServiceDto) {

		validateDeliveryStaffById(requestServiceDto.deliveryStaffId());

		StaffType staffType = StaffType.HUB_STAFF;

		DeliveryStaff deliveryStaff = DeliveryStaff.create(
			requestServiceDto.deliveryStaffId(),
			staffType,
			calculateDeliveryOrder(staffType)
		);
		return HubDeliveryStaffCreateResponse.from(deliveryStaffRepository.save(deliveryStaff));
	}

	@Transactional
	public CompanyDeliveryStaffCreateResponse createCompanyDeliveryStaff(CompanyDeliveryStaffCreate requestServiceDto) {
		validateDeliveryStaffById(requestServiceDto.deliveryStaffId());

		StaffType staffType = StaffType.COMPANY_STAFF;

		DeliveryStaff deliveryStaff = DeliveryStaff.create(
			requestServiceDto.deliveryStaffId(),
			staffType,
			calculateDeliveryOrder(staffType)
		);

		DeliveryStaffHub deliveryStaffHub = DeliveryStaffHub.create(
			deliveryStaff,
			UUID.fromString(requestServiceDto.hubId())
		);

		return CompanyDeliveryStaffCreateResponse.from(deliveryStaff,
			deliveryStaffHubRepository.save(deliveryStaffHub));
	}

	public Page<DeliveryStaffListResponse> getDeliveryStaffList(Pageable pageable) {
		Page<DeliveryStaff> deliveryStaffPage = deliveryStaffRepository.findByIsDeletedFalse(pageable);
		return deliveryStaffPage.map(DeliveryStaffListResponse::from);

	}

	public Page<DeliveryStaffListResponse> searchDeliveryStaff(DeliveryStaffSearchCond cond, Pageable pageable) {
		Page<DeliveryStaff> deliveryStaffPage = deliveryStaffRepository.findAllDeliveryStaffByStaffTypeAndDeliveryOrder(
			cond, pageable);

		return deliveryStaffPage.map(DeliveryStaffListResponse::from);
	}

	public DeliveryStaffDetailResponse getDeliveryStaffDetail(Long deliveryStaffId) {
		DeliveryStaff deliveryStaff = findDeliveryStaffById(deliveryStaffId);
		String hubId = null;

		if (deliveryStaff.getStaffType().isSame(StaffType.COMPANY_STAFF)) {
			hubId = findDeliveryStaffHubById(deliveryStaffId).getHubId().toString();
		}

		return DeliveryStaffDetailResponse.from(deliveryStaff, hubId);
	}

	@Transactional
	public DeliveryStaffDetailResponse updateDeliveryStaff(Long deliveryStaffId, DeliveryStaffUpdateRequest request) {
		DeliveryStaff deliveryStaff = findDeliveryStaffById(deliveryStaffId);

		StaffType newStaffType = getStaffType(request.staffType());

		// StaffType이 변경된 경우 처리
		if (!deliveryStaff.getStaffType().isSame(newStaffType)) {
			return handleStaffTypeChange(deliveryStaff, newStaffType, request.hubId());
		}

		// StaffType이 동일하며 hubId만 변경되는 경우 처리
		if (newStaffType.isSame(StaffType.COMPANY_STAFF)) {
			return handleHubIdChange(deliveryStaffId, request.hubId(), deliveryStaff);
		}

		// StaffType도 변경되지 않고 HubId도 변경되지 않은 경우
		return DeliveryStaffDetailResponse.from(deliveryStaff, null);
	}

	@Transactional
	public void deleteDeliveryStaff(Long deliveryStaffId) {
		DeliveryStaff deliveryStaff = findDeliveryStaffById(deliveryStaffId);

		deliveryStaff.deleteBase(1L);

		if (deliveryStaff.getStaffType().isSame(StaffType.COMPANY_STAFF)) {
			DeliveryStaffHub deliveryStaffHub = findDeliveryStaffHubById(deliveryStaffId);
			deliveryStaffHub.deleteBase(1L);
		}
	}

	/* UTIL */

	private void validateDeliveryStaffById(Long deliveryStaffId) {
		if (deliveryStaffRepository.existsById(deliveryStaffId))
			throw new DeliveryException(ErrorCode.DUPLICATED_DELIVERY_STAFF);
	}

	private DeliveryStaff findDeliveryStaffById(Long deliveryStaffId) {
		return deliveryStaffRepository.findById(deliveryStaffId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_STAFF_NOT_FOUND));
	}

	private DeliveryStaffHub findDeliveryStaffHubById(Long deliveryStaffId) {
		return deliveryStaffHubRepository.findByDeliveryStaffId(deliveryStaffId)
			.orElseThrow(() -> new DeliveryException(ErrorCode.DELIVERY_STAFF_HUB_NOT_FOUND));
	}

	private StaffType getStaffType(String staffType) {
		StaffType newStaffType = StaffType.of(staffType);
		if (newStaffType == null) {
			throw new DeliveryException(ErrorCode.UNSUPPORTED_STAFF_TYPE);
		}
		return newStaffType;
	}

	private Long calculateDeliveryOrder(StaffType staffType) {
		DeliveryStaff lastDeliveryStaff = deliveryStaffRepository.findTopByStaffTypeOrderByCreatedAtDesc(staffType);
		if (lastDeliveryStaff == null)
			return 0L;
		else
			return lastDeliveryStaff.getDeliveryOrder() + 1L;

	}

	private DeliveryStaffDetailResponse handleStaffTypeChange(DeliveryStaff deliveryStaff, StaffType newStaffType,
		String hubId) {
		Long updatedDeliveryOrder = calculateDeliveryOrder(newStaffType);

		deliveryStaff.update(newStaffType, updatedDeliveryOrder);

		if (!newStaffType.isSame(StaffType.COMPANY_STAFF)) {
			DeliveryStaffHub deliveryStaffHub = findDeliveryStaffHubById(deliveryStaff.getId());
			deliveryStaffHub.deleteBase(1L); // 임시 사용자 ID, 추후 수정 필요
			return DeliveryStaffDetailResponse.from(deliveryStaff, null);
		}

		DeliveryStaffHub deliveryStaffHub = DeliveryStaffHub.create(
			deliveryStaff,
			UUID.fromString(hubId) // hubId 유효성 검사 필요
		);
		return DeliveryStaffDetailResponse.from(deliveryStaff,
			deliveryStaffHubRepository.save(deliveryStaffHub).getHubId().toString());

	}

	private DeliveryStaffDetailResponse handleHubIdChange(Long deliveryStaffId, String hubId,
		DeliveryStaff deliveryStaff) {
		DeliveryStaffHub deliveryStaffHub = findDeliveryStaffHubById(deliveryStaffId);
		deliveryStaffHub.update(UUID.fromString(hubId)); // hubId 유효성 검사 필요

		return DeliveryStaffDetailResponse.from(deliveryStaff,
			deliveryStaffHubRepository.save(deliveryStaffHub).getHubId().toString());
	}

}
