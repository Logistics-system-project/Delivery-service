package com.spring.dozen.delivery.presentation.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreateResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryListResponse;
import com.spring.dozen.delivery.application.service.DeliveryHistoryService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.PageResponse;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryCreateRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;

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

	@GetMapping
	public PageResponse<DeliveryHistoryListResponse> getDeliveryHistoryList(
		PaginationRequest request,
		DeliveryHistorySearchCond cond
	){
		Pageable pageable = PageUtil.toPageable(request);

		return PageResponse.of(deliveryHistoryService.getDeliveryHistoryList(pageable, cond));
	}
}
