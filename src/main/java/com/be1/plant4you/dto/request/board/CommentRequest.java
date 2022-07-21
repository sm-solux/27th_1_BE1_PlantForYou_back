package com.be1.plant4you.dto.request.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "댓글|대댓글 등록, 수정 정보 전달 DTO")
@Getter
public class CommentRequest {

    @Schema(description = "댓글 내용", example = "좋은 정보 감사합니다!", required = true)
    private String content;
}
