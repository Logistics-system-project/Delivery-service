package com.spring.dozen.delivery.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.CompanyDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.dto.HubDeliveryStaffCreateResponse;
import com.spring.dozen.delivery.application.service.DeliveryStaffService;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.CompanyDeliveryStaffCreateRequest;
import com.spring.dozen.delivery.presentation.dto.HubDeliveryStaffCreateRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-staff")
@RestController
public class DeliveryStaffController {

	private final DeliveryStaffService deliveryStaffService;

	@PostMapping("/hub")
	public ApiResponse<HubDeliveryStaffCreateResponse> createHubDeliveryStaff(
		@RequestBody HubDeliveryStaffCreateRequest requestDto
	){
		return ApiResponse.success(deliveryStaffService.createHubDeliveryStaff(requestDto.toServiceDto()));
	}

	@PostMapping("/company")
	public ApiResponse<CompanyDeliveryStaffCreateResponse> createCompanyDeliveryStaff(
		@RequestBody CompanyDeliveryStaffCreateRequest requestDto
	){
		return ApiResponse.success(deliveryStaffService.createCompanyDeliveryStaff(requestDto.toServiceDto()));
	}
}
