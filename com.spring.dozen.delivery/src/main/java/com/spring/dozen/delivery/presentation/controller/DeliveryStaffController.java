package com.spring.dozen.delivery.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.annotation.RequireRole;
import com.spring.dozen.delivery.application.dto.deliveryStaff.CompanyDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.dto.deliveryStaff.DeliveryStaffDetailResponse;
import com.spring.dozen.delivery.application.dto.deliveryStaff.DeliveryStaffListResponse;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.presentation.dto.PageResponse;
import com.spring.dozen.delivery.presentation.dto.deliveryStaff.DeliveryStaffSearchCond;
import com.spring.dozen.delivery.application.dto.deliveryStaff.HubDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.service.DeliveryStaffService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.deliveryStaff.CompanyDeliveryStaffCreateRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryStaff.DeliveryStaffUpdateRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryStaff.HubDeliveryStaffCreateRequest;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-staff")
@RestController
public class DeliveryStaffController {

	private final DeliveryStaffService deliveryStaffService;

	@PostMapping("/hub")
	@RequireRole({"MASTER"})
	public ApiResponse<HubDeliveryStaffCreateResponse> createHubDeliveryStaff(
		@RequestBody @Valid HubDeliveryStaffCreateRequest requestDto
	) {
		return ApiResponse.success(deliveryStaffService.createHubDeliveryStaff(requestDto.toServiceDto()));
	}

	@PostMapping("/company")
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public ApiResponse<CompanyDeliveryStaffCreateResponse> createCompanyDeliveryStaff(
		@RequestBody @Valid CompanyDeliveryStaffCreateRequest requestDto
	) {
		return ApiResponse.success(deliveryStaffService.createCompanyDeliveryStaff(requestDto.toServiceDto()));
	}

	@GetMapping
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public PageResponse<DeliveryStaffListResponse> getDeliveryStaffList(
		PaginationRequest request,
		DeliveryStaffSearchCond cond
	) {
		Pageable pageable = PageUtil.toPageable(request);

		return PageResponse.of(deliveryStaffService.getDeliveryStaffList(pageable, cond));
	}

	@GetMapping("/{deliveryStaffId}")
	@RequireRole({"MASTER", "HUB_MANAGER", "HUB_DELIVERY_STAFF"})
	public ApiResponse<DeliveryStaffDetailResponse> getDeliveryStaffDetail(
		@PathVariable Long deliveryStaffId
	) {
		return ApiResponse.success(deliveryStaffService.getDeliveryStaffDetail(deliveryStaffId));
	}

	@PatchMapping("/{deliveryStaffId}")
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public ApiResponse<DeliveryStaffDetailResponse> updateDeliveryStaff(
		@PathVariable Long deliveryStaffId,
		@RequestBody DeliveryStaffUpdateRequest request
	) {
		return ApiResponse.success(deliveryStaffService.updateDeliveryStaff(deliveryStaffId, request));
	}

	@DeleteMapping("/{deliveryStaffId}")
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public ApiResponse<Void> deleteDeliveryStaff(
		@PathVariable Long deliveryStaffId
	){
		deliveryStaffService.deleteDeliveryStaff(deliveryStaffId);
		return ApiResponse.success();
	}
}
