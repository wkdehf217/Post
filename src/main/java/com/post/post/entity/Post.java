package com.post.post.entity;

import com.post.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Post {

    private Long id;
    private Long pw;
    private String title;
    private String username;
    private String content;
    private String date;

    public Post(PostRequestDto requestDto) {
        this.pw = requestDto.getPw();
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.date = requestDto.getDate();
    }

    public void update(PostRequestDto requestDto) {
        this.pw = requestDto.getPw();
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.date = requestDto.getDate();
    }
}