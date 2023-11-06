package com.post.post.service;

import com.post.post.controller.PostNotFoundException;
import com.post.post.dto.PostRequestDto;
import com.post.post.dto.PostResponseDto;
import com.post.post.entity.Post;
import com.post.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {  // PostService 라는 이름으로 Bean에 등록

    private final com.post.post.repository.PostRepository PostRepository;

    public PostService(PostRepository PostRepository) {
        this.PostRepository = PostRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        // RequestDto -> Entity
        Post Post = new Post(requestDto);

        // DB 저장
        Post savePost = PostRepository.save(Post);

        // Entity -> ResponseDto
        PostResponseDto PostResponseDto = new PostResponseDto(savePost);

        return PostResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        // DB 조회
        return PostRepository.findAll();
    }

    public List<PostResponseDto> getPost(Long id) {
        // DB 조회
        return PostRepository.findPost(id);
    }

    public PostResponseDto updatePost(Long id, Long pw, PostRequestDto requestDto) {
        // DB 수정
        // 해당 게시판이 DB에 존재하는지 확인
        Post Post =  PostRepository.findById(id,pw);
        if(Post != null) {
            // Post 내용 수정
            PostRepository.update(id, pw,requestDto);

            return new PostResponseDto(id,requestDto.getTitle(),requestDto.getUsername(), requestDto.getContent(),Post.getDate());
        } else {
            throw new PostNotFoundException();
        }
    }

    public Long deletePost(Long id, Long pw) {
        // DB 삭제
        // 해당 게시판이 DB에 존재하는지 확인
        Post Post =  PostRepository.findById(id,pw);
        if(Post != null) {
            // Post 삭제
            PostRepository.delete(id, pw);

            return id;
        } else {
            throw new PostNotFoundException();
        }
    }


}