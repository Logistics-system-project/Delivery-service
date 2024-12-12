package com.spring.dozen.delivery.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreate;
import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreateResponse;
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

		return CompanyDeliveryStaffCreateResponse.from(deliveryStaff, deliveryStaffHubRepository.save(deliveryStaffHub));
	}

	private void validateDeliveryStaffById(Long deliveryStaffId) {
		if (deliveryStaffRepository.existsById(deliveryStaffId))
			throw new DeliveryException(ErrorCode.DUPLICATED_DELIVERY_STAFF);
	}

	private Long calculateDeliveryOrder(StaffType staffType) {
		DeliveryStaff lastDeliveryStaff = deliveryStaffRepository.findTopByStaffTypeOrderByCreatedAtDesc(staffType);
		if (lastDeliveryStaff == null)
			return 0L;
		else
			return lastDeliveryStaff.getDeliveryOrder() + 1L;

	}
}
