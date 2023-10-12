package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long >, OrderRepositoryCustom {
    @Query("select o from Order o where o.id = :id")
    public Optional<Order> findByOne(@Param("id")Long id);
}
