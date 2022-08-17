package com.sparta.pineapple.model;

import com.sparta.pineapple.dto.request.BasketRequestDto;
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
public class Basket extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "product_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private int count;


    public void update(BasketRequestDto basketRequestDto) {
        this.count = basketRequestDto.getCount();
    }

}