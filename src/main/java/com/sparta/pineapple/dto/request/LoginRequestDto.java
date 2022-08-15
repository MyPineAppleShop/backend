package com.sparta.pineapple.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank
    private String memberName;
    @NotBlank
    private String password;
}