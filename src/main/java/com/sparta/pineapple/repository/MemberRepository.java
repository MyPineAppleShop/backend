package com.sparta.pineapple.repository;

import com.sparta.pineapple.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findById(Long id);
  Optional<Member> findByMemberName(String memberName);
}
