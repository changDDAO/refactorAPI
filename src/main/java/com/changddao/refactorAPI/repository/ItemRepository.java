package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>,ItemRepositoryCustom {

}
