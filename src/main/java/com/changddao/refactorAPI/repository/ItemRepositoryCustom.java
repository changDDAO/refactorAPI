package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Item;

public interface ItemRepositoryCustom {
    public Item findOne(Long id);
}
