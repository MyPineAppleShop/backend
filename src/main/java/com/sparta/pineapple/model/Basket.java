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

    @Column(nullable = false)
    private String distinction;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int cost;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int count;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public void update(BasketRequestDto basketRequestDto) {
        this.count = basketRequestDto.getCount();
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }

}