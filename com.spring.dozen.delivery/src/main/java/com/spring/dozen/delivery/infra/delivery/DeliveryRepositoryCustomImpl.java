package com.spring.dozen.delivery.infra.delivery;

import static com.spring.dozen.delivery.domain.entity.QDelivery.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.dozen.delivery.domain.entity.Delivery;
import com.spring.dozen.delivery.domain.enums.DeliveryStatus;
import com.spring.dozen.delivery.presentation.dto.delivery.DeliverySearchCond;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryRepositoryCustomImpl implements DeliveryRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	public Page<Delivery> findDeliveries(DeliverySearchCond cond, Pageable pageable) {
		List<Delivery> deliveryList = queryFactory
			.select(delivery)
			.from(delivery)
			.where(
				statusEq(cond.status()),
				departureHubIdEq(cond.departureHubId()),
				arrivalHubIdEq(cond.arrivalHubId())
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(Wildcard.count)
			.from(delivery)
			.where(
				statusEq(cond.status()),
				departureHubIdEq(cond.departureHubId()),
				arrivalHubIdEq(cond.arrivalHubId())
			)
			.fetchOne();

		return new PageImpl<>(deliveryList, pageable, total);
	}

	private BooleanExpression statusEq(String status) {
		return Objects.nonNull(status) ? delivery.status.eq(DeliveryStatus.of(status)) : null;
	}

	private BooleanExpression departureHubIdEq(String departureHubId) {
		return Objects.nonNull(departureHubId) ? delivery.departureHubId.eq(UUID.fromString(departureHubId)) : null;
	}

	private BooleanExpression arrivalHubIdEq(String arrivalHubId) {
		return Objects.nonNull(arrivalHubId) ? delivery.arrivalHubId.eq(UUID.fromString(arrivalHubId)) : null;
	}
}
