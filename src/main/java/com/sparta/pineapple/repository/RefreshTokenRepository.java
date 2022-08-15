package com.sparta.pineapple.repository;


import com.sparta.pineapple.model.Member;
import com.sparta.pineapple.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByMember(Member member);
}