package com.changddao.refactorAPI.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.changddao.refactorAPI.dto.QOrderQueryDto is a Querydsl Projection type for OrderQueryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderQueryDto extends ConstructorExpression<OrderQueryDto> {

    private static final long serialVersionUID = -1073661464L;

    public QOrderQueryDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDateTime> orderDate, com.querydsl.core.types.Expression<com.changddao.refactorAPI.domain.OrderStatus> orderStatus, com.querydsl.core.types.Expression<? extends com.changddao.refactorAPI.domain.Address> address) {
        super(OrderQueryDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDateTime.class, com.changddao.refactorAPI.domain.OrderStatus.class, com.changddao.refactorAPI.domain.Address.class}, id, name, orderDate, orderStatus, address);
    }

}

