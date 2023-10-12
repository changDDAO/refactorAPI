package com.changddao.refactorAPI.service;

import com.changddao.refactorAPI.RefactorApiApplication;
import com.changddao.refactorAPI.RefactorApiApplicationTests;
import com.changddao.refactorAPI.domain.*;
import com.changddao.refactorAPI.domain.items.Book;
import com.changddao.refactorAPI.exception.NotEnoughStockException;
import com.changddao.refactorAPI.repository.ItemRepository;
import com.changddao.refactorAPI.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void 상품주문() {
        //given
        Member member = createMember("changho", "daegu", "chunsuro", "261");
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //then
        Order order = orderRepository.findByOne(orderId).get();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
    }

    @Test
    public void 상품주문_재고수량초과() {
        //given
        Member member = createMember("changho", "대구", "청수로", "261");
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;
        //when

        //then
        assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class);

    }
    @Test
    public void 주문취소(){
    //given
        Member member = createMember("changho", "대구", "청수로", "261");
        Item item = createBook("시골JPA", 10000, 10);
        int orderCount =2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

    //then
        Order findOrder = orderRepository.findByOne(orderId).get();
        assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(10);

    }

    private Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setUsername(name);
        member.setAddress(new Address(city, street, zipcode));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setItemName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        em.persist(book);
        return book;
    }

}