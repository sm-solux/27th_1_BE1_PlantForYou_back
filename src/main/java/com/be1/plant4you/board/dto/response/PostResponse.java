package com.be1.plant4you.board.dto.response;

import com.be1.plant4you.board.enumerate.PostCat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@Schema(description = "게시글 상세내용 조회 시 반환하는 게시글 DTO")
@Getter
public class PostResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "게시글 작성자 이름", example = "다은")
    private String writerName;

    @Schema(description = "게시글 작성일", example = "07/21 20:30")
    @JsonFormat(pattern = "MM/dd HH:mm", shape = STRING)
    private LocalDateTime createdDate;

    @Schema(description = "게시글 카테고리", allowableValues = {"정보", "질문", "사담"})
    private PostCat cat;

    @Schema(description = "게시글 제목", example = "다육이 키우는 방법")
    private String title;

    @Schema(description = "게시글 내용", example = "다육이는 ~~~")
    private String content;

    @Schema(description = "게시글 조회수", example = "10")
    private Long hits;

    @Schema(description = "게시글 좋아요수", example = "15")
    private Long likes;

    @Schema(description = "게시글 스크랩수", example = "20")
    private Long scraps;

    @Schema(description = "현재 로그인한 이용자가 작성한 글인지 여부", example = "true")
    private Boolean isMyPost;

    @Schema(description = "현재 로그인한 이용자의 해당 글에 대한 좋아요 여부", example = "true")
    private Boolean isLikes;

    @Schema(description = "현재 로그인한 이용자의 해당 글에 대한 스크랩 여부", example = "false")
    private Boolean isScrap;

    @Schema(description = "게시글에 대한 댓글 리스트")
    private List<CommentResponse> commentList = new ArrayList<>();

    public PostResponse(Long postId, String writerName, LocalDateTime createdDate,
                        PostCat cat, String title, String content,
                        Long hits, Long likes, Long scraps,
                        Boolean isMyPost, Boolean isLikes, Boolean isScrap) {
        this.postId = postId;
        this.writerName = writerName;
        this.createdDate = createdDate;
        this.cat = cat;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
        this.scraps = scraps;
        this.isMyPost = isMyPost;
        this.isLikes = isLikes;
        this.isScrap = isScrap;
    }

    public void changeCommentList(List<CommentResponse> commentList) {
        this.commentList = commentList;
    }
}