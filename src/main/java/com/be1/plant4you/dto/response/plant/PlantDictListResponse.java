package com.be1.plant4you.dto.response.plant;

import com.be1.plant4you.enumerate.plant.Func;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "식물사전 리스트 조회 시 반환하는 식물사전 DTO")
@Getter
@AllArgsConstructor
public class PlantDictListResponse {

    @Schema(description = "식물사전 ID", example = "1")
    private Long plantDictId;

    @Schema(description = "식물 이름", example = "다육이")
    private String name;

    @Schema(description = "볕양 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte sunAmtLevel;

    @Schema(description = "난이도 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte difficultyLevel;

    @Schema(description = "사이즈 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte sizeLevel;

    @Schema(description = "한 줄 기능", example = "공기정화")
    private Func funcHead;

    @Schema(description = "식물 이미지 url", example = "http://")
    private String imgUrl;
}
