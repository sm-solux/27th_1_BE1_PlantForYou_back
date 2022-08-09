package com.be1.plant4you.plant.repository.custom;

import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;

import java.util.List;

public interface PlantDictRepositoryCustom {

    PlantScoreResponse findByPlantScore(Byte sunLevel, Byte hardLevel, Boolean isEdible, Boolean isToxic, Byte sizeLevel);
    List<PlantDictListResponse> findAllDto();
    PlantDictResponse findDtoById(Long plantDictId);
}
