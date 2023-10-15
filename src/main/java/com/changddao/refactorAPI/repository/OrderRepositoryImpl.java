package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.*;
import com.changddao.refactorAPI.dto.OrderQueryDto;
import com.changddao.refactorAPI.dto.QOrderQueryDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.changddao.refactorAPI.domain.QDelivery.*;
import static com.changddao.refactorAPI.domain.QMember.*;
import static com.changddao.refactorAPI.domain.QOrder.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom{
    JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> findAllUseQueryDsl(OrderSearch orderSearch) {
        List<Order> result = queryFactory.select(order).from(order)
                .leftJoin(order.member, member).fetchJoin()
                .leftJoin(order.delivery, delivery).fetchJoin()
                .where(usernameEq(orderSearch.getMemberName())
                        , orderStatusEq(orderSearch))
                .fetch();
        return result;
    }

    private BooleanExpression usernameEq(String memberName) {
        return StringUtils.hasText(memberName)? member.username.eq(memberName):null;
    }

    private BooleanExpression orderStatusEq(OrderSearch orderSearch) {
        if (orderSearch.getOrderStatus() != null) {
            if(orderSearch.getOrderStatus()==OrderStatus.ORDER)
            return order.orderStatus.eq(OrderStatus.ORDER);
            else return order.orderStatus.eq(OrderStatus.CANCEL);
        }
        return null;
    }

    @Override
    public List<OrderQueryDto> findOrderQueryDto(OrderSearch orderSearch) {
        List<OrderQueryDto> result = queryFactory.select(new QOrderQueryDto(order.id,
                        member.username, order.createdDate, order.orderStatus, delivery.address))
                .from(order)
                .leftJoin(order.member, member)
                .leftJoin(order.delivery, delivery)
                .fetch();
        return result;
    }
}
