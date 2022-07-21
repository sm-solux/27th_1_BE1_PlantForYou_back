package com.be1.plant4you.dto.request.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "게시글 수정 정보 전달 DTO")
@Getter
public class PostUpdateRequest {

    @Schema(description = "게시글 제목", example = "다육이 키우는 방법", required = true)
    private String title;

    @Schema(description = "게시글 내용", example = "다육이는 ~~~", required = true)
    private String content;
}
