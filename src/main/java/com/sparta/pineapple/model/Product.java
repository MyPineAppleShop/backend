package com.sparta.pineapple.model;

import com.sparta.pineapple.dto.request.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String distinction;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int count = 1;


    public void update(ProductRequestDto basketRequestDto) {
        this.cost = basketRequestDto.getCost();
    }


}
