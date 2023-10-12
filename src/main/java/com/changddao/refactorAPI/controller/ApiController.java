package com.changddao.refactorAPI.controller;

import com.changddao.refactorAPI.domain.Order;
import com.changddao.refactorAPI.domain.OrderSearch;
import com.changddao.refactorAPI.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        OrderSearch orderSearch = new OrderSearch();
        List<Order> result = orderRepository.findAllUseQueryDsl(orderSearch);
        return result;
        //error 에러발생
    }
}