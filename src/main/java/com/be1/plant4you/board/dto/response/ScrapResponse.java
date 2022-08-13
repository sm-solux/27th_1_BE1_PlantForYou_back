package com.be1.plant4you.board.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시글 스크랩 등록, 취소 시 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScrapResponse {

    @Schema(description = "게시글 스크랩수", example = "20")
    private Long scraps;

    @Schema(description = "현재 로그인한 이용자의 해당 글에 대한 스크랩 여부", example = "false")
    private Boolean isScrap;
}
