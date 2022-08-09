package com.be1.plant4you.plant.repository.custom;

import com.be1.plant4you.plant.dto.response.PlantDictListResponse;
import com.be1.plant4you.plant.dto.response.PlantDictResponse;
import com.be1.plant4you.plant.dto.response.PlantScoreResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.be1.plant4you.plant.domain.QPlantDict.plantDict;
import static com.be1.plant4you.plant.domain.QPlantScore.plantScore;

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
                                plantDict.sunAmtLevel,
                                plantDict.difficultyLevel,
                                plantDict.sizeLevel,
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
                                plantDict.sunAmtLevel,
                                plantDict.sunAmtDesc,
                                plantDict.difficultyLevel,
                                plantDict.difficultyDesc,
                                plantDict.sizeLevel,
                                plantDict.sizeDesc,
                                plantDict.funcHead,
                                plantDict.plantDesc,
                                plantDict.imgUrl
                        )
                )
                .from(plantDict)
                .where(plantDict.id.eq(plantDictId))
                .fetchOne();
    }
}
