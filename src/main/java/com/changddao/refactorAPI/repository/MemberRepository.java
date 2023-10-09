package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
