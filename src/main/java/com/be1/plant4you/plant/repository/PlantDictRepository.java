package com.be1.plant4you.plant.repository;

import com.be1.plant4you.plant.domain.PlantDict;
import com.be1.plant4you.plant.repository.custom.PlantDictRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDictRepository extends JpaRepository<PlantDict, Long>, PlantDictRepositoryCustom {
}
