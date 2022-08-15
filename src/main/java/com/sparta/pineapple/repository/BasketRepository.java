package com.sparta.pineapple.repository;

import com.sparta.pineapple.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}