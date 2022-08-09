package com.be1.plant4you.plant.dto.response;

import com.be1.plant4you.plant.enumerate.Func;
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

    @Schema(description = "볕양 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte sunAmtLevel;

    @Schema(description = "볕양 설명", example = "햇빛을 많이 필요로 합니다.")
    private String sunAmtDesc;

    @Schema(description = "난이도 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte difficultyLevel;

    @Schema(description = "난이도 설명", example = "물을 자주 주어야 하므로 난이도가 높은 식물입니다.")
    private String difficultyDesc;

    @Schema(description = "사이즈 레벨", allowableValues = {"1", "2", "3", "4", "5"})
    private Byte sizeLevel;

    @Schema(description = "사이즈 설명", example = "사이즈가 큰 식물이므로 거실이나 베란다에 두어야 합니다.")
    private String sizeDesc;

    @Schema(description = "한 줄 기능", example = "공기정화")
    private Func funcHead;

    @Schema(description = "식물 설명", example = "다육이는 어쩌구 저쩌구...")
    private String plantDesc;

    @Schema(description = "식물 이미지 url", example = "http://")
    private String imgUrl;
}
