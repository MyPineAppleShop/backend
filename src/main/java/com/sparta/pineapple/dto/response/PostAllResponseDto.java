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
public class PostAllResponseDto {
    private Long id;
    private String title;
    private String author;
    private int likesCount;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}