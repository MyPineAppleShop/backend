package com.sparta.pineapple.repository;

import com.sparta.pineapple.model.Basket;
import com.sparta.pineapple.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}