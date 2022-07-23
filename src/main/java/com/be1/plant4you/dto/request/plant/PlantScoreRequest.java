package com.be1.plant4you.dto.request.plant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "식물 mbti 결과 요청 DTO")
@Getter
public class PlantScoreRequest {

    @Schema(description = "볕양", allowableValues = {"0", "1"}, required = true)
    private int sunLevel;

    @Schema(description = "난이도", allowableValues = {"0", "1"}, required = true)
    private int hardLevel;

    @Schema(description = "식용여부", example = "true", required = true)
    private Boolean isEdible;

    @Schema(description = "독성여부", example = "false", required = true)
    private Boolean isToxic;

    @Schema(description = "사이즈", allowableValues = {"0", "1", "2"}, required = true)
    private int sizeLevel;
}
