package com.post.post.dto;

import com.post.post.entity.Post;
import lombok.Getter;

import java.util.Date;

@Getter
public class PostResponseDto {
    private Long id;
    //private Long pw;
    private String title;
    private String username;
    private String contents;
    private String date;

    public PostResponseDto(Post Post) {
        this.id = Post.getId();
        //this.pw = Post.getPw();
        this.title = Post.getTitle();
        this.username = Post.getUsername();
        this.contents = Post.getContent();
        this.date = Post.getDate();
    }

    public PostResponseDto(Long id, String username, String contents, String title, String date) {
        this.id = id;
        //this.pw = pw;
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.date = date;
    }
}