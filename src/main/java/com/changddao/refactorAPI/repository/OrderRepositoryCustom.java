package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Order;
import com.changddao.refactorAPI.domain.OrderSearch;
import com.changddao.refactorAPI.dto.OrderQueryDto;

import java.util.List;

public interface OrderRepositoryCustom {
    public List<Order> findAllUseQueryDsl(OrderSearch orderSearch);

    public List<OrderQueryDto> findOrderQueryDto(OrderSearch orderSearch);
}
