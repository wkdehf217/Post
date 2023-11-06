/*
package com.Post.Post.controller;

import com.Post.Post.dto.PostRequestDto;
import com.Post.Post.dto.PostResponseDto;
import com.Post.Post.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Map<Long, Post> PostList = new HashMap<>();

    @PostMapping("/Posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post Post = new Post(requestDto);

        // Post Max ID Check
        Long maxId = PostList.size() > 0 ? Collections.max(PostList.keySet()) + 1 : 1; // PostList의 keySet을 가져와서 + 1, 없으면 1을 가져와
        Post.setId(maxId);

        // DB 저장
        PostList.put(Post.getId(), Post);

        // Entity -> ResponseDto
        PostResponseDto PostResponseDto = new PostResponseDto(Post);

        return PostResponseDto;
    }

    // 조회
    @GetMapping("/Posts")
    public List<PostResponseDto> getPosts() { // List인 이유는 메모가 하나일리 없어서
        // Map To List
        List<PostResponseDto> responseList = PostList.values().stream() // stream : 하나씩 for문처럼 돌려줌
                .map(PostResponseDto::new).toList();                    // map : 변환

        return responseList;
    }

    // Json 으로 받아온다고하면 RequestBody 눈치
    @PutMapping("/Posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        if (PostList.containsKey(id)) {
            // 해당 메모 가져오기
            Post Post = PostList.get(id);

            // 메모 수정
            Post.update(requestDto);
            return Post.getId(); // = id
        } else {
            throw new IllegalArgumentException("존재하지 않는 메모입니다.");
        }
    }

    @DeleteMapping("/Posts/{id}")
    public Long deletePost(@PathVariable Long id) { // 지우는거니까 RequestBody 필요 x
        // 해당 메모가 DB에 존재하는지 확인
        if (PostList.containsKey(id)) {
            // 해당 메모 가져오기
            PostList.remove(id);

            return id;
        } else {
            throw new IllegalArgumentException("존재하지 않는 메모입니다.");
        }
    }
}
*/

package com.post.post.controller;

import com.post.post.dto.PostRequestDto;
import com.post.post.dto.PostResponseDto;
import com.post.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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