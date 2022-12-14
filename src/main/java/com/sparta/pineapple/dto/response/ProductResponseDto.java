package com.sparta.pineapple.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
  private long id;
  private String distinction;
  private String productName;
  private int cost;
  private String image;
  private int count;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}