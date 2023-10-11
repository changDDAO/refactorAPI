package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Item;
import com.changddao.refactorAPI.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.changddao.refactorAPI.domain.QItem.*;

public class ItemRepositoryImpl implements ItemRepositoryCustom{
    JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Item findOne(Long id) {
       return queryFactory.select(item)
                .from(item)
                .where(item.id.eq(id))
                .fetchOne();
    }
}
