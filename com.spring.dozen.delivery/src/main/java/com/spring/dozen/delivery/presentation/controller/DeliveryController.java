package com.spring.dozen.delivery.presentation.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dozen.delivery.application.annotation.RequireRole;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryCreateResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryDetailResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryListResponse;
import com.spring.dozen.delivery.application.dto.delivery.DeliveryStatusUpdateResponse;
import com.spring.dozen.delivery.application.service.DeliveryService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.PageResponse;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryCreateRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryStatusUpdateRequest;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliveryUpdateRequest;

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
		return ApiResponse.success(deliveryService.createDelivery(request.toServiceDto()));
	}

	@GetMapping
	public PageResponse<DeliveryListResponse> getDeliveryList(
		PaginationRequest request,
		DeliverySearchCond cond
	){
		Pageable pageable = PageUtil.toPageable(request);

		return PageResponse.of(deliveryService.getDeliveryList(pageable, cond));
	}

	@GetMapping("/{deliveryId}")
	public ApiResponse<DeliveryDetailResponse> getDeliveryDetail(
		@PathVariable UUID deliveryId,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryService.getDeliveryDetail(deliveryId, userId, role));
	}

	@PutMapping("/{deliveryId}")
	@RequireRole({"MASTER", "HUB_MANAGER", "HUB_DELIVERY_STAFF"})
	public ApiResponse<DeliveryDetailResponse> updateDelivery(
		@PathVariable UUID deliveryId,
		@RequestBody DeliveryUpdateRequest request,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryService.updateDelivery(deliveryId, request.toServiceDto(), userId, role));
	}

	@PatchMapping("/status/{deliveryId}")
	@RequireRole({"MASTER", "HUB_MANAGER", "HUB_DELIVERY_STAFF"})
	public ApiResponse<DeliveryStatusUpdateResponse> updateDeliveryStatus(
		@PathVariable UUID deliveryId,
		@RequestBody DeliveryStatusUpdateRequest request,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryService.updateDeliveryStatus(deliveryId, request.toServiceDto(), userId, role));
	}

	@DeleteMapping("/{deliveryId}")
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public ApiResponse<Void> deleteDelivery(
		@PathVariable UUID deliveryId,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		deliveryService.deleteDelivery(deliveryId, userId, role);
		return ApiResponse.success();
	}
}
