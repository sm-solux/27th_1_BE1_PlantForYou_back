package com.be1.plant4you.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Schema(description = "댓글|대댓글 등록, 수정 정보 전달 DTO")
@Getter
public class CommentRequest {

    @NotBlank
    @Schema(description = "댓글 내용", example = "좋은 정보 감사합니다!", required = true)
    private String content;
}
