package com.post.post.controller;

import com.post.post.dto.ResponseErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ResponseErrorDto> internalServerError(PostNotFoundException e) {
        ResponseErrorDto responseErrorDto = ResponseErrorDto.builder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorDto);
    }
}