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
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreate;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.exception.DeliveryException;
import com.spring.dozen.delivery.application.exception.ErrorCode;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.entity.DeliveryStaffHub;
import com.spring.dozen.delivery.domain.enums.StaffType;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffHubRepository;
import com.spring.dozen.delivery.domain.repository.DeliveryStaffRepository;

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

	public Page<DeliveryStaffListResponse> searchDeliveryStaff(String searchedBy, String keyword, Pageable pageable) {
		switch (searchedBy) {
			case "staffType":
				return deliveryStaffRepository.findByStaffType(StaffType.of(keyword), pageable)
					.map(DeliveryStaffListResponse::from);
			case "deliveryOrder":
				return deliveryStaffRepository.findByDeliveryOrder(Long.parseLong(keyword), pageable)
					.map(DeliveryStaffListResponse::from);
			default:
				throw new DeliveryException(ErrorCode.INVALID_SEARCH_CONDITION);
		}
	}

	public DeliveryStaffDetailResponse getDeliveryStaffDetail(Long deliveryStaffId) {
		DeliveryStaff deliveryStaff = findDeliveryStaffById(deliveryStaffId);
		String hubId = null;

		if (deliveryStaff.getStaffType().isSame(StaffType.COMPANY_STAFF)) {
			hubId = findDeliveryStaffHubById(deliveryStaffId).getHubId().toString();
		}

		return DeliveryStaffDetailResponse.from(deliveryStaff, hubId);
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

	private Long calculateDeliveryOrder(StaffType staffType) {
		DeliveryStaff lastDeliveryStaff = deliveryStaffRepository.findTopByStaffTypeOrderByCreatedAtDesc(staffType);
		if (lastDeliveryStaff == null)
			return 0L;
		else
			return lastDeliveryStaff.getDeliveryOrder() + 1L;

	}
}
