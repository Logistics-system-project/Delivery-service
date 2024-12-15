package com.spring.dozen.delivery.infra.deliveryHistory;

import static com.spring.dozen.delivery.domain.entity.QDeliveryHistory.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.dozen.delivery.domain.entity.DeliveryHistory;
import com.spring.dozen.delivery.domain.enums.DeliveryHistoryStatus;
import com.spring.dozen.delivery.presentation.dto.deliveryHistory.DeliveryHistorySearchCond;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryHistoryRepositoryCustomImpl implements DeliveryHistoryRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<DeliveryHistory> findAllDeliveryHistoryByCond(Pageable pageable, DeliveryHistorySearchCond cond) {
		List<DeliveryHistory> deliveryHistories = queryFactory
			.select(deliveryHistory)
			.from(deliveryHistory)
			.where(
				deliveryIdEq(cond.deliveryId()),
				deliveryStaffIdEq(cond.deliveryStaffId()),
				departureHubIdEq(cond.departureHubId()),
				arrivalHubIdEq(cond.arrivalHubId())
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(Wildcard.count)
			.from(deliveryHistory)
			.where(
				statusEq(cond.status()),
				deliveryIdEq(cond.deliveryId()),
				deliveryStaffIdEq(cond.deliveryStaffId()),
				departureHubIdEq(cond.departureHubId()),
				arrivalHubIdEq(cond.arrivalHubId())
			)
			.fetchOne();
		return new PageImpl<>(deliveryHistories, pageable, total);
	}

	private BooleanExpression statusEq(String status) {
		return Objects.nonNull(status) ? deliveryHistory.status.eq(DeliveryHistoryStatus.of(status)) : null;
	}
	private BooleanExpression deliveryIdEq(UUID deliveryId) {
		return Objects.nonNull(deliveryId) ? deliveryHistory.delivery.id.eq(deliveryId) : null;
	}
	private BooleanExpression deliveryStaffIdEq(Long deliveryStaffId) {
		return Objects.nonNull(deliveryStaffId) ? deliveryHistory.deliveryStaff.id.eq(deliveryStaffId) : null;
	}
	private BooleanExpression departureHubIdEq(UUID departureHubId) {
		return Objects.nonNull(departureHubId) ? deliveryHistory.departureHubId.eq(departureHubId) : null;
	}
	private BooleanExpression arrivalHubIdEq(UUID arrivalHubId) {
		return Objects.nonNull(arrivalHubId) ? deliveryHistory.arrivalHubId.eq(arrivalHubId) : null;
	}

}
