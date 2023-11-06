package com.post.post.controller;

public class PostNotFoundException extends RuntimeException {

    private static final String MESSAGE = "ID나 비밀번호가 틀렸습니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}