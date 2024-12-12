package com.spring.dozen.delivery.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.dto.DeliveryStaffListResponse;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.service.DeliveryStaffService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.CompanyDeliveryStaffCreateRequest;
import com.spring.dozen.delivery.presentation.dto.HubDeliveryStaffCreateRequest;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-staff")
@RestController
public class DeliveryStaffController {

	private final DeliveryStaffService deliveryStaffService;

	@PostMapping("/hub")
	public ApiResponse<HubDeliveryStaffCreateResponse> createHubDeliveryStaff(
		@RequestBody @Valid HubDeliveryStaffCreateRequest requestDto
	){
		return ApiResponse.success(deliveryStaffService.createHubDeliveryStaff(requestDto.toServiceDto()));
	}

	@PostMapping("/company")
	public ApiResponse<CompanyDeliveryStaffCreateResponse> createCompanyDeliveryStaff(
		@RequestBody @Valid CompanyDeliveryStaffCreateRequest requestDto
	){
		return ApiResponse.success(deliveryStaffService.createCompanyDeliveryStaff(requestDto.toServiceDto()));
	}

	@GetMapping
	public ApiResponse<Page<DeliveryStaffListResponse>> getDeliveryStaffList(
		PaginationRequest request,
		@RequestParam(required = false) String searchedBy,
		@RequestParam(required = false) String keyword
	) {
		Pageable pageable = PageUtil.toPageable(request);

		if(searchedBy!=null && keyword!=null) return ApiResponse.success(deliveryStaffService.searchDeliveryStaff(searchedBy, keyword, pageable));
		return ApiResponse.success(deliveryStaffService.getDeliveryStaffList(pageable));
	}
}
