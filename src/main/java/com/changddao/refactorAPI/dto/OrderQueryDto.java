package com.changddao.refactorAPI.dto;

import com.changddao.refactorAPI.domain.Address;
import com.changddao.refactorAPI.domain.Order;
import com.changddao.refactorAPI.domain.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderQueryDto {
    private Long id;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    @QueryProjection
    public OrderQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}




