package com.post.post.controller;

import com.post.post.dto.PostRequestDto;
import com.post.post.dto.PostResponseDto;
import com.post.post.service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService PostService;

    public PostController(PostService PostService) {
        this.PostService = PostService;
    }

    @PostMapping("/Post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return PostService.createPost(requestDto);
    }

    @GetMapping("/Posts")
    public List<PostResponseDto> getPosts() {
        return PostService.getPosts();
    }

    @GetMapping("/Post/{id}")
    public List<PostResponseDto> getPost(@PathVariable Long id) {
        return PostService.getPost(id);
    }

    @PutMapping("/Post/{id}/{pw}")
    public PostResponseDto updatePost(@PathVariable Long id,@PathVariable Long pw, @RequestBody PostRequestDto requestDto) {
        return PostService.updatePost(id, pw, requestDto);
    }

    @DeleteMapping("/Post/{id}/{pw}")
    public Long deletePost(@PathVariable Long id,@PathVariable Long pw) {
        return PostService.deletePost(id, pw);
    }
}