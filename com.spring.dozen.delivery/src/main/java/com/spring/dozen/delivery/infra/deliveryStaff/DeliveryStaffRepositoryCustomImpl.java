package com.spring.dozen.delivery.infra.deliveryStaff;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.dozen.delivery.presentation.dto.deliveryStaff.DeliveryStaffSearchCond;
import com.spring.dozen.delivery.domain.entity.DeliveryStaff;
import com.spring.dozen.delivery.domain.enums.StaffType;

import static com.spring.dozen.delivery.domain.entity.QDeliveryStaff.deliveryStaff;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryStaffRepositoryCustomImpl implements DeliveryStaffRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<DeliveryStaff> findAllDeliveryStaffByStaffTypeAndDeliveryOrder(DeliveryStaffSearchCond cond, Pageable pageable) {
		List<DeliveryStaff> deliveryStaffs = queryFactory
			.select(deliveryStaff)
			.from(deliveryStaff)
			.where(
				staffTypeEq(cond.staffType()),
				deliveryOrderEq(cond.deliveryOrder())
			)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory
			.select(Wildcard.count)
			.from(deliveryStaff)
			.where(
				staffTypeEq(cond.staffType()),
				deliveryOrderEq(cond.deliveryOrder())
			)
			.fetchOne();

		return new PageImpl<>(deliveryStaffs, pageable, total);
	}

	private BooleanExpression staffTypeEq(String staffType) {
		return Objects.nonNull(staffType) ? deliveryStaff.staffType.eq(StaffType.of(staffType)) : null;
	}

	private BooleanExpression deliveryOrderEq(Long deliveryOrder){
		return Objects.nonNull(deliveryOrder) ? deliveryStaff.deliveryOrder.eq(deliveryOrder) : null;
	}
}
