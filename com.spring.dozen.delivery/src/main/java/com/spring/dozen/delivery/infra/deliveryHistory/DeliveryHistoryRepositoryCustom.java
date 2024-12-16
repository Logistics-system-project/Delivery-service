package com.spring.dozen.delivery.infra.deliveryHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;

public interface DeliveryHistoryRepositoryCustom {
	Page<DeliveryHistory> findAllDeliveryHistoryByCond(Pageable pageable, DeliveryHistorySearchCond cond);

}
