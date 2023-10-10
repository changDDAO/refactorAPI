package com.changddao.refactorAPI.repository;

import com.changddao.refactorAPI.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
 public List<Member> findByUsername(String name);
}
