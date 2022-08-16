package com.be1.plant4you.board.dto.response;

import com.be1.plant4you.board.enumerate.PostCat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "게시글만 조회 시 반환하는 게시글 DTO")
@Getter @Builder
@AllArgsConstructor
public class PostOnlyResponse {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "게시글 카테고리", allowableValues = {"정보", "질문", "사담"})
    private PostCat cat;

    @Schema(description = "게시글 제목", example = "다육이 키우는 방법")
    private String title;

    @Schema(description = "게시글 내용", example = "다육이는 ~~~")
    private String content;
}
