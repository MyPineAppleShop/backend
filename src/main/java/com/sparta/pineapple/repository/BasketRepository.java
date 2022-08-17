package com.sparta.pineapple.repository;

import com.sparta.pineapple.model.Basket;
import com.sparta.pineapple.model.Member;
import com.sparta.pineapple.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByMember(Member member);

    Optional<Basket> findByProduct(Product product);
}