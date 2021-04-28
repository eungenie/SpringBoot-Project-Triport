package com.project.triport.responseDto.results;

import com.project.triport.entity.Post;
import com.project.triport.entity.User;
import com.project.triport.responseDto.results.property.AccessUserResponseDto;
import com.project.triport.responseDto.results.property.AuthorResponseDto;
import com.project.triport.responseDto.results.property.CommentResponseDto;
import com.project.triport.responseDto.results.property.information.PostInformationResponseDto;

import java.util.List;

public class PostDetailResponseDto {

    private PostInformationResponseDto information;
    private AuthorResponseDto authorResponseDto;
    private List<CommentResponseDto> commentResponseDtoList;
    private AccessUserResponseDto user;

    public PostDetailResponseDto(Post post, User accessUser) {
        this.information = new PostInformationResponseDto(post);
        this.authorResponseDto = new AuthorResponseDto(post);
        this.user = new AccessUserResponseDto(post, accessUser);
    }
}
