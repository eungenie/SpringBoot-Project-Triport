package com.project.triport.entity;

import com.project.triport.requestDto.PostRequestDto;
import com.project.triport.requestDto.VideoUrlDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String videoType;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private Boolean posPlay;

    @Column(nullable = false)
    private Long likeNum;

    @ElementCollection
    private List<String> hashtag;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

//    public Post(PostRequestDto requestDto, Member member){
//        this.videoUrl = requestDto.getVideoUrl();
//        this.likeNum = 0L;
//        this.hashtag = requestDto.getHashtag();
//        this.member = member;
//    }

    public Post(String videoUrl, boolean posPlay, List<String> hashtag, Member member) {
        this.videoType = "mp4";
        this.videoUrl = videoUrl;
        this.posPlay = posPlay;
        this.likeNum = 0L;
        this.hashtag = hashtag;
        this.member = member;
    }

    public void update(PostRequestDto requestDto) {
        this.hashtag = requestDto.getHashtag();
    }

    public void updateUrl(VideoUrlDto requestDto) {
        this.videoType = "m3u8";
        this.posPlay = true;
        this.videoUrl = requestDto.getVideoUrl();
    }

    public void plusLikeNum() {
        this.likeNum++;
    }

    public void minusLikeNum() {
        this.likeNum--;
    }
}
