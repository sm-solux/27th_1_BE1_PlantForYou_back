package com.be1.plant4you.dto.request.plant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Schema(description = "식물 mbti 결과 요청 DTO")
@Getter
public class PlantScoreRequest {

    @NotNull
    @Min(0) @Max(2)
    @Schema(description = "볕양", allowableValues = {"0", "1", "2"}, required = true)
    private Byte sunLevel;

    @NotNull
    @Min(0) @Max(1)
    @Schema(description = "난이도", allowableValues = {"0", "1"}, required = true)
    private Byte hardLevel;

    @NotNull
    @Schema(description = "식용여부", example = "true", required = true)
    private Boolean isEdible;

    @NotNull
    @Schema(description = "독성여부", example = "false", required = true)
    private Boolean isToxic;

    @NotNull
    @Min(0) @Max(2)
    @Schema(description = "사이즈", allowableValues = {"0", "1", "2"}, required = true)
    private Byte sizeLevel;

    public void setFalseToIsToxic() {
        isToxic = false;
    }
}
