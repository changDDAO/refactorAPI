package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Member;
import com.changddao.refactorAPI.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static com.changddao.refactorAPI.domain.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom{
    JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Member findOne(Long id) {
        Member findMember = queryFactory.select(member)
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
        return findMember;
    }
}
