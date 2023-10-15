package com.changddao.refactorAPI.controller;

import com.changddao.refactorAPI.domain.Address;
import com.changddao.refactorAPI.domain.Order;
import com.changddao.refactorAPI.domain.OrderSearch;
import com.changddao.refactorAPI.domain.OrderStatus;
import com.changddao.refactorAPI.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final OrderRepository orderRepository;


    OrderSearch orderSearch = new OrderSearch();
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> result = orderRepository.findAllUseQueryDsl(orderSearch);
        for (Order order : result) {
            order.getMember().getUsername();
            order.getMember().getAddress();
        }
        return result;
        //error 에러발생
        //lazy loading 때문
    }
    @GetMapping("api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllUseQueryDsl(orderSearch);
        return orders.stream().map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @Data
    public static class SimpleOrderDto {
        private Long id;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            id = order.getId();
            name = order.getMember().getUsername();
            orderDate = order.getCreatedDate();
            orderStatus = order.getOrderStatus();
            address = order.getDelivery().getAddress();
        }
    }
}