package com.be1.plant4you.dto.request.plant;

import lombok.Getter;

@Getter
public class PlantScoreRequest {

    private int sunLevel;

    private int hardLevel;

    private Boolean isEdible;

    private Boolean isToxic;

    private int sizeLevel;
}
