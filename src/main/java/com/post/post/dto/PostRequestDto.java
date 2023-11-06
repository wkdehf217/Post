package com.post.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private Long pw;
    private String title;
    private String username;
    private String content;
    private String date;
}