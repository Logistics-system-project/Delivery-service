package com.spring.dozen.delivery.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.DeliveryCreateResponse;
import com.spring.dozen.delivery.application.service.DeliveryService;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.DeliveryCreateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/delivery")
@RestController
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping
	public ApiResponse<DeliveryCreateResponse> createDelivery(
		@RequestBody @Valid DeliveryCreateRequest request
	){
		return ApiResponse.success(deliveryService.createDelivery(request));
	}
}
