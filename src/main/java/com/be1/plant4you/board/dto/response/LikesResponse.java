package com.be1.plant4you.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시글 좋아요 등록, 취소 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesResponse {

    @Schema(description = "게시글 좋아요수", example = "15")
    private Long likes;

    @Schema(description = "현재 로그인한 이용자의 해당 글에 대한 좋아요 여부", example = "true")
    private Boolean isLikes;
}
