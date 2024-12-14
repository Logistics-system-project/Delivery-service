package com.spring.dozen.delivery.presentation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.delivery.DeliveryCreateResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryResponse;
import com.spring.dozen.delivery.application.service.DeliveryService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryCreateRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;

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

	@GetMapping
	public ApiResponse<Page<DeliveryResponse>> getDeliveryList(
		PaginationRequest request,
		DeliverySearchCond cond
	){
		Pageable pageable = PageUtil.toPageable(request);

		return ApiResponse.success(deliveryService.getDeliveryList(pageable, cond));
	}
}
