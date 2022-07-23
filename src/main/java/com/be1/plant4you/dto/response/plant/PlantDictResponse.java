package com.be1.plant4you.dto.response.plant;

import lombok.Getter;

@Getter
public class PlantDictResponse {

    private Long plantDictId;

    private String name;

    private int viabilityLevel;

    private String viabilityDesc;

    private int waterFreqLevel;

    private String waterFreqDesc;

    private int sunAmtLevel;

    private String sunAmtDesc;

    private String funcHead;

    private String funcDesc;

    private String plantDesc;

    private String imgUrl;
}
