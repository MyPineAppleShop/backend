package com.sparta.pineapple.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBasketResponseDto<T> {
  private boolean success;
  private T data;
  private long basketTotalCost;
  private Error error;

  public static <T> GetBasketResponseDto<T> success(T data, long basketTotalCost) {
    return new GetBasketResponseDto<>(true, data, basketTotalCost, null);
  }

  public static <T> GetBasketResponseDto<T> fail(String code, String message) {
    return new GetBasketResponseDto<>(false, null, 0, new Error(code, message));
  }

  @Getter
  @AllArgsConstructor
  static class Error {
    private String code;
    private String message;
  }

}