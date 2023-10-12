package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.*;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.util.List;

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

}
