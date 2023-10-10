package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberSimpleTest(){
    //given
        Member member1 = new Member("changho");
        em.persist(member1);


    //when
        Member findMember = memberRepository.findById(1L).get();
        Assertions.assertThat(member1).isEqualTo(findMember);

    //then

    }

}