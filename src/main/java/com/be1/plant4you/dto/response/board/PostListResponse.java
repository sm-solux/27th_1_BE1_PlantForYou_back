package com.be1.plant4you.dto.response.board;

import com.be1.plant4you.enumerate.board.PostCat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "게시글 리스트 조회 시 반환하는 게시글 DTO")
@Getter
@AllArgsConstructor
public class PostListResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "게시글 카테고리", allowableValues = {"정보", "질문", "사담"})
    private PostCat cat;

    @Schema(description = "게시글 제목", example = "다육이 키우는 방법")
    private String title;

    @Schema(description = "게시글 조회수", example = "10")
    private Long hits;

    @Schema(description = "게시글 좋아요수", example = "15")
    private Long likes;

    @Schema(description = "게시글 스크랩수", example = "20")
    private Long scraps;
}
