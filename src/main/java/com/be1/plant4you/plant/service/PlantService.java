package com.be1.plant4you.plant.service;

import com.be1.plant4you.common.exception.CustomException;
import com.be1.plant4you.plant.dto.request.PlantScoreRequest;
import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;
import com.be1.plant4you.plant.repository.PlantDictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantDictRepository plantDictRepository;

    public PlantScoreResponse getPlantScoreResult(PlantScoreRequest plantScoreRequest) {
        //식용 true, 독성 true 경우 -> 독성 false 변경
        if (plantScoreRequest.getIsEdible() && plantScoreRequest.getIsToxic()) {
            plantScoreRequest.setFalseToIsToxic();
        }

        return plantDictRepository.findByPlantScore(
                plantScoreRequest.getSunLevel(),
                plantScoreRequest.getHardLevel(),
                plantScoreRequest.getIsEdible(),
                plantScoreRequest.getIsToxic(),
                plantScoreRequest.getSizeLevel()
        );
    }

    public List<PlantDictListResponse> getPlantDictList() {
        return plantDictRepository.findAllDto();
    }

    public PlantDictResponse getPlantDict(Long plantDictId) {
        plantDictRepository.findById(plantDictId).orElseThrow(() -> new CustomException(NOT_FOUND_PLANT));
        return plantDictRepository.findDtoById(plantDictId);
    }
}
