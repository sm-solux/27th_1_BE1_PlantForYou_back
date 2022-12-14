package com.be1.plant4you.board.dto.request;

import com.be1.plant4you.board.enumerate.PostCat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.be1.plant4you.common.validation.ValidationGroup.*;

@Schema(description = "게시글 등록, 수정 정보 전달 DTO")
@Getter
public class PostRequest {

    @NotNull(groups = {PostSave.class}, message = "게시글 카테고리를 선택해주세요.")
    @Schema(description = "게시글 카테고리", allowableValues = {"정보", "질문", "사담"}, required = true)
    private PostCat cat;

    @NotBlank(groups = {PostSave.class, PostUpdate.class}, message = "게시글 제목을 작성해주세요.")
    @Schema(description = "게시글 제목", example = "다육이 키우는 방법", required = true)
    private String title;

    @NotBlank(groups = {PostSave.class, PostUpdate.class}, message = "게시글 내용을 작성해주세요.")
    @Schema(description = "게시글 내용", example = "다육이는 ~~~", required = true)
    private String content;
}
