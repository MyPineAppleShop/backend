package com.sparta.pineapple.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.pineapple.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketResponseDto {
  private long id;
  private long productId;
  private String distinction;
  private String productName;
  private int cost;
  private String image;
  private int count;
  private long totalCost;
  private long basketTotalCost;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}