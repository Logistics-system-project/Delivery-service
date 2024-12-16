package com.spring.dozen.delivery.application.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubClient {
	// 허브 존재 여부 조회
	@GetMapping("/api/hub/{hubId}/exists")
	boolean existsHubByHubId(@PathVariable UUID hubId);

	// 허브 관리자 ID 조회
	@GetMapping("/api/hub/{hubId}/manager")
	Long findUserIdByHubId(@PathVariable UUID hubId);

}
