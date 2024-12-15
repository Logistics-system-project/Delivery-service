package com.spring.dozen.delivery.presentation.controller;

import java.util.UUID;

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
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryCreateResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryDetailResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryListResponse;
import com.spring.dozen.delivery.application.dto.deliveryHistory.DeliveryHistoryStatusUpdateResponse;
import com.spring.dozen.delivery.application.service.DeliveryHistoryService;
import com.spring.dozen.delivery.application.util.PageUtil;
import com.spring.dozen.delivery.presentation.dto.ApiResponse;
import com.spring.dozen.delivery.presentation.dto.PageResponse;
import com.spring.dozen.delivery.presentation.dto.PaginationRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryCreateRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryStatusUpdateRequest;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistoryUpdateRequest;

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

	@GetMapping("/{deliveryHistoryId}")
	public ApiResponse<DeliveryHistoryDetailResponse> getDeliveryHistoryDetail(
		@PathVariable UUID deliveryHistoryId,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryHistoryService.getDeliveryHistoryDetail(deliveryHistoryId, userId, role));
	}

	@PutMapping("/{deliveryHistoryId}")
	@RequireRole({"MASTER", "HUB_MANAGER", "HUB_DELIVERY_STAFF"})
	public ApiResponse<DeliveryHistoryDetailResponse> updateDeliveryHistory(
		@PathVariable UUID deliveryHistoryId,
		@RequestBody DeliveryHistoryUpdateRequest request,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryHistoryService.updateDeliveryHistory(deliveryHistoryId, request.toServiceDto(), userId, role));
	}

	@PatchMapping("/{deliveryHistoryId}")
	@RequireRole({"MASTER", "HUB_MANAGER", "HUB_DELIVERY_STAFF"})
	public ApiResponse<DeliveryHistoryStatusUpdateResponse> updateDeliveryHistoryStatus(
		@PathVariable UUID deliveryHistoryId,
		@RequestBody DeliveryHistoryStatusUpdateRequest request,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		return ApiResponse.success(deliveryHistoryService.updateDeliveryHistoryStatus(deliveryHistoryId, request.toServiceDto(), userId, role));
	}

	@DeleteMapping("/{deliveryHistoryId}")
	@RequireRole({"MASTER", "HUB_MANAGER"})
	public ApiResponse<Void> deleteDeliveryHistory(
		@PathVariable UUID deliveryHistoryId,
		@RequestHeader(value = "X-User-Id", required = true) String userId,
		@RequestHeader(value = "X-Role", required = true) String role
	){
		deliveryHistoryService.deleteDeliveryHistory(deliveryHistoryId, userId, role);
		return ApiResponse.success();
	}
}
