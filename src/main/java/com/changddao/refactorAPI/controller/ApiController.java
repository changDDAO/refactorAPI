package com.changddao.refactorAPI.controller;

import com.changddao.refactorAPI.domain.Address;
import com.changddao.refactorAPI.domain.Order;
import com.changddao.refactorAPI.domain.OrderSearch;
import com.changddao.refactorAPI.domain.OrderStatus;
import com.changddao.refactorAPI.dto.OrderQueryDto;
import com.changddao.refactorAPI.repository.OrderRepository;
import com.changddao.refactorAPI.service.OrderService;
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
    private final OrderService orderService;


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
    @GetMapping("api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllUseQueryDsl(orderSearch);
        return orders.stream().map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }
    //화면 뷰에 맞춘 설계
    // 쿼리는 v3에 비해 퍼올리는게 적기 때문에 효율이 미세하게 좋을 지 모르나
    //재 사용성이 떨어지기 때문에 V3, V4에 따른 trade-off가 있음
    @GetMapping("api/v4/simple-orders")
    public List<OrderQueryDto> ordersV4() {
        return orderService.transacFindOrderQueryDto();
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