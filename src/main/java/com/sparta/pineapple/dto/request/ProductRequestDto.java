package com.sparta.pineapple.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String distinction;
    private String productName;
    private int cost;
    private String image;
    private int count;
}