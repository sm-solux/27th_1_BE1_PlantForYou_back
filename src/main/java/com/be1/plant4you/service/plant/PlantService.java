package com.be1.plant4you.service.plant;

import com.be1.plant4you.dto.request.plant.PlantScoreRequest;
import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;
import com.be1.plant4you.repository.plant.PlantDictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantDictRepository plantDictRepository;

    public PlantScoreResponse getPlantScoreResult(PlantScoreRequest plantScoreRequest) {
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
        return plantDictRepository.findDtoById(plantDictId);
    }
}
