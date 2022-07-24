package com.be1.plant4you.repository.plant.custom;

import com.be1.plant4you.dto.response.plant.PlantDictListResponse;
import com.be1.plant4you.dto.response.plant.PlantDictResponse;
import com.be1.plant4you.dto.response.plant.PlantScoreResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.be1.plant4you.domain.plant.QPlantDict.*;
import static com.be1.plant4you.domain.plant.QPlantScore.*;

@RequiredArgsConstructor
public class PlantDictRepositoryImpl implements PlantDictRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PlantScoreResponse findByPlantScore(Byte sunLevel, Byte hardLevel, Boolean isEdible, Boolean isToxic, Byte sizeLevel) {
        return queryFactory
                .select(
                        Projections.constructor(PlantScoreResponse.class,
                                plantDict.id,
                                plantDict.name,
                                plantDict.imgUrl
                        )
                )
                .from(plantDict)
                .join(plantDict.plantScore, plantScore)
                .on(
                        plantScore.sunLevel.eq(sunLevel),
                        plantScore.hardLevel.eq(hardLevel),
                        plantScore.isEdible.eq(isEdible),
                        plantScore.isToxic.eq(isToxic),
                        plantScore.sizeLevel.eq(sizeLevel)
                )
                .fetchOne();
    }

    @Override
    public List<PlantDictListResponse> findAllDto() {
        return queryFactory
                .select(
                        Projections.constructor(PlantDictListResponse.class,
                                plantDict.id,
                                plantDict.name,
                                plantDict.viabilityLevel,
                                plantDict.waterFreqLevel,
                                plantDict.sunAmtLevel,
                                plantDict.funcHead,
                                plantDict.imgUrl
                        )
                )
                .from(plantDict)
                .fetch();
    }

    @Override
    public PlantDictResponse findDtoById(Long plantDictId) {
        return queryFactory
                .select(
                        Projections.constructor(PlantDictResponse.class,
                                plantDict.id,
                                plantDict.name,
                                plantDict.viabilityLevel,
                                plantDict.viabilityDesc,
                                plantDict.waterFreqLevel,
                                plantDict.waterFreqDesc,
                                plantDict.sunAmtLevel,
                                plantDict.sunAmtDesc,
                                plantDict.funcHead,
                                plantDict.funcDesc,
                                plantDict.plantDesc,
                                plantDict.imgUrl
                        )
                )
                .from(plantDict)
                .where(plantDict.id.eq(plantDictId))
                .fetchOne();
    }
}
