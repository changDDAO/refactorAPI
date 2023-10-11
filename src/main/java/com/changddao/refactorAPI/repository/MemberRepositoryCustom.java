package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Member;

public interface MemberRepositoryCustom {
    public Member findOne(Long id);
}
