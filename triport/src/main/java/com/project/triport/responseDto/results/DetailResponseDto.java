package com.project.triport.responseDto.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.triport.entity.BasicBoard;
import com.project.triport.entity.Post;
import com.project.triport.responseDto.results.property.AccessUserResponseDto;
import com.project.triport.responseDto.results.property.AuthorResponseDto;
import com.project.triport.responseDto.results.property.CommentResponseDto;
import com.project.triport.responseDto.results.property.information.BasicBoardInformationResponseDto;
import com.project.triport.responseDto.results.property.information.InformationResponseDto;
import com.project.triport.responseDto.results.property.information.PostInformationResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailResponseDto {

    private InformationResponseDto information;
    private AuthorResponseDto author;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommentResponseDto> commentList;
    private AccessUserResponseDto member;


    public DetailResponseDto(Post post, Boolean isLike){
        this.information = new PostInformationResponseDto(post);
        this.author = new AuthorResponseDto(post);
        this.member = new AccessUserResponseDto(isLike);
    }

    public DetailResponseDto(BasicBoard basicBoard, Boolean isLike, List<CommentResponseDto> commentResponseDtoList) {
        this.information = new BasicBoardInformationResponseDto(basicBoard);
        this.author = new AuthorResponseDto(basicBoard);
        this.member = new AccessUserResponseDto(isLike);
        this.commentList = commentResponseDtoList;
    }
}
