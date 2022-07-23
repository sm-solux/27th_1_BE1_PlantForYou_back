package com.be1.plant4you.dto.response.plant;

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

    @Schema(description = "생존력 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int viabilityLevel;

    @Schema(description = "물빈도 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int waterFreqLevel;

    @Schema(description = "볕양 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int sunAmtLevel;

    @Schema(description = "한 줄 기능", example = "공기청정")
    private String funcHead;

    @Schema(description = "식물 이미지 url", example = "http://")
    private String imgUrl;
}
