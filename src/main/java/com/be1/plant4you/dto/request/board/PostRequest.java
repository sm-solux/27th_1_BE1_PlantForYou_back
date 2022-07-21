package com.be1.plant4you.dto.request.board;

import com.be1.plant4you.enumerate.board.PostCat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "게시글 등록 정보 전달 DTO")
@Getter
public class PostRequest {

    @Schema(description = "게시글 카테고리", example = "정보|질문|사담", required = true)
    private PostCat cat;

    @Schema(description = "게시글 제목", example = "다육이 키우는 방법", required = true)
    private String title;

    @Schema(description = "게시글 내용", example = "다육이는 ~~~", required = true)
    private String content;
}
