package com.sparta.pineapple.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketRequestDto {
    private String distinction;
    private String productName;
    private int cost;
    private String image;
    private int count;
}