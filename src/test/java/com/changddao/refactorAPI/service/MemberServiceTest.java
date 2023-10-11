package com.changddao.refactorAPI.service;

import com.changddao.refactorAPI.domain.Member;
import com.changddao.refactorAPI.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
    //given
        Member member = new Member();
        member.setUsername("Kim");

        Long saveId = memberService.join(member);
        //when
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));


    //then
    }
    @Test
    public void 회원_중복_테스트(){
    //given
        Member member1 = new Member();
        member1.setUsername("Kim");
        Member member2 = new Member();
        member2.setUsername("Kim");
    //when
        memberService.join(member1);
        memberService.join(member2);
        //then
        fail("예외가 발생해야 한다.");

    }

}