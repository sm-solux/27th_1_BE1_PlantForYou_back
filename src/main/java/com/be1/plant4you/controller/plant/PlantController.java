package com.be1.plant4you.controller.plant;

import com.be1.plant4you.dto.request.plant.PlantScoreRequest;
import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/plants")
@Controller
public class PlantController {

    /**
     * 인증되지 않은 이용자도 요청 가능
     */
    @GetMapping("/mbti")
    public PlantScoreResponse getPlantScoreResult(@RequestBody PlantScoreRequest plantScoreRequest) {
        return new PlantScoreResponse();
    }

    /**
     * 식물사전 리스트 조회
     * @return
     */
    @GetMapping
    public List<PlantDictListResponse> getPlantDicts() {
        return new ArrayList<>();
    }

    /**
     * 식물사전 상세내용 조회
     */
    @GetMapping("/{plantDictId}")
    public PlantDictResponse getPlantDict(@PathVariable Long plantDictId) {
        return new PlantDictResponse();
    }
}
