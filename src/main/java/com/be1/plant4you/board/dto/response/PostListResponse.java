package com.be1.plant4you.board.dto.response;

import com.be1.plant4you.board.enumerate.PostCat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "게시글 리스트 조회 시 반환하는 게시글 DTO")
@Getter
@AllArgsConstructor
public class PostListResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

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
}
