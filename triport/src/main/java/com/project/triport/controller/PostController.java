package com.project.triport.controller;

import com.project.triport.responseDto.PostListResponseDto;
import com.project.triport.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api/posts")
    public PostListResponseDto readPostsAll(){
        return postService.readPostsAll();
    }

}
