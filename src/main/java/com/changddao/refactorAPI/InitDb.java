package com.changddao.refactorAPI;

import com.changddao.refactorAPI.domain.*;
import com.changddao.refactorAPI.domain.items.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void dbInit() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = new Member();
            member.setUsername("userA");
            member.setAddress(new Address("대구", "청수로", "261"));
            em.persist(member);

            Book book1 = new Book();
            book1.setItemName("JPA1 Book");
            book1.setStockQuantity(100);
            book1.setPrice(10000);
            em.persist(book1);

            Book book2 = new Book();
            book2.setItemName("JPA2 Book");
            book2.setStockQuantity(100);
            book2.setPrice(20000);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Member member = new Member();
            member.setUsername("userB");
            member.setAddress(new Address("부산", "사하구", "123"));
            em.persist(member);

            Book book1 = new Book();
            book1.setItemName("Spring1 Book");
            book1.setStockQuantity(200);
            book1.setPrice(20000);
            em.persist(book1);

            Book book2 = new Book();
            book2.setItemName("Spring2 Book");
            book2.setStockQuantity(300);
            book2.setPrice(40000);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

    }
}
