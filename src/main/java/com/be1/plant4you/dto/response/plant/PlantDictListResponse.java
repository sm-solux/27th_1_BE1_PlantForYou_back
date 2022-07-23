package com.be1.plant4you.dto.response.plant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlantDictListResponse {

    private Long plantDictId;

    private String name;

    private int viabilityLevel;

    private int waterFreqLevel;

    private int sunAmtLevel;

    private String funcHead;

    private String imgUrl;
}
