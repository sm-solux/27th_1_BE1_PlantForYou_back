package com.be1.plant4you.plant.controller;

import com.be1.plant4you.plant.dto.request.PlantScoreRequest;
import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;
import com.be1.plant4you.plant.service.PlantService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "식물 관련 API")
@RequiredArgsConstructor
@RequestMapping("/api/plants")
@RestController
public class PlantController {

    private final PlantService plantService;

    @Operation(summary = "식물 mbti 결과 반환", description = "인증되지 않은 이용자도 요청 가능")
    @GetMapping("/mbti")
    public PlantScoreResponse getPlantScoreResult(@RequestBody @Validated PlantScoreRequest plantScoreRequest) {
        return plantService.getPlantScoreResult(plantScoreRequest);
    }

    @Operation(summary = "식물사전 리스트 조회", description = "일단 인증되지 않은 이용자도 요청 가능")
    @GetMapping
    public List<PlantDictListResponse> getPlantDictList() {
        return plantService.getPlantDictList();
    }

    @Operation(summary = "식물사전 상세내용 조회", description = "일단 인증되지 않은 이용자도 요청 가능")
    @GetMapping("/{plantDictId}")
    public PlantDictResponse getPlantDict(@PathVariable Long plantDictId) {
        return plantService.getPlantDict(plantDictId);
    }
}
