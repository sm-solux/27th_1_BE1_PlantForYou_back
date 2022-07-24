package com.be1.plant4you.service.plant;

import com.be1.plant4you.domain.plant.PlantDict;
import com.be1.plant4you.dto.request.plant.PlantScoreRequest;
import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;
import com.be1.plant4you.exception.plant.WrongPlantDictIdException;
import com.be1.plant4you.repository.plant.PlantDictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.be1.plant4you.exception.plant.PlantErrorCode.*;

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
        Optional<PlantDict> plantDictOptional = plantDictRepository.findById(plantDictId);
        if (plantDictOptional.isEmpty()) {
            throw new WrongPlantDictIdException(WRONG_PLANT_DICT_ID);
        }
        return plantDictRepository.findDtoById(plantDictId);
    }
}
