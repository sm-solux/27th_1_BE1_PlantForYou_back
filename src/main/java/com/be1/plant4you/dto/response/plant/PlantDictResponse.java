package com.be1.plant4you.dto.response.plant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "식물사전 상세내용 조회 시 반환하는 식물사전 DTO")
@Getter
@AllArgsConstructor
public class PlantDictResponse {

    @Schema(description = "식물사전 ID", example = "1")
    private Long plantDictId;

    @Schema(description = "식물 이름", example = "다육이")
    private String name;

    @Schema(description = "생존력 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int viabilityLevel;

    @Schema(description = "생존력 설명", example = "생존력이 강한 식물입니다.")
    private String viabilityDesc;

    @Schema(description = "물빈도 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int waterFreqLevel;

    @Schema(description = "물빈도 설명", example = "물을 자주 주지 않아도 됩니다.")
    private String waterFreqDesc;

    @Schema(description = "볕양 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private int sunAmtLevel;

    @Schema(description = "볕양 설명", example = "햇빛을 많이 필요로 합니다.")
    private String sunAmtDesc;

    @Schema(description = "한 줄 기능", example = "공기청정")
    private String funcHead;

    @Schema(description = "기능 설명", example = "공기청정 기능에 탁월한 식물입니다.")
    private String funcDesc;

    @Schema(description = "식물 설명", example = "다육이는 어쩌구 저쩌구...")
    private String plantDesc;

    @Schema(description = "식물 이미지 url", example = "http://")
    private String imgUrl;
}
