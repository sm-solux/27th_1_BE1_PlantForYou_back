package com.be1.plant4you.board.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.*;

import static com.be1.plant4you.common.validation.ValidationGroup.*;

@Schema(description = "댓글|대댓글 등록, 수정 정보 전달 DTO")
@Getter
public class CommentRequest {

    @NotNull(message = "게시글을 선택해주세요.", groups = {CommentSave.class})
    @Schema(description = "게시글 id", example = "1", required = true)
    private Long postId;

    @Schema(description = "부모 댓글 id", example = "1")
    private Long parentId;

    @NotBlank(message = "댓글을 작성해주세요.", groups = {CommentSave.class, CommentUpdate.class})
    @Schema(description = "댓글 내용", example = "좋은 정보 감사합니다!", required = true)
    private String content;
}
