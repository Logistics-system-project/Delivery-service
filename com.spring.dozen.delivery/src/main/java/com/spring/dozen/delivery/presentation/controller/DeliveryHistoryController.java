package com.spring.dozen.delivery.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreateResponse;
import com.spring.dozen.delivery.application.service.DeliveryHistoryService;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryCreateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/delivery-history")
@RestController
public class DeliveryHistoryController {

	private final DeliveryHistoryService deliveryHistoryService;

	@PostMapping
	public ApiResponse<DeliveryHistoryCreateResponse> createDeliveryHistory(
		@RequestBody @Valid DeliveryHistoryCreateRequest request
	){
		return ApiResponse.success(deliveryHistoryService.createDeliveryHistory(request.toServiceDto()));
	}

}
