package com.be1.plant4you.repository.plant;

import com.be1.plant4you.domain.plant.PlantDict;
import com.be1.plant4you.repository.plant.custom.PlantDictRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDictRepository extends JpaRepository<PlantDict, Long>, PlantDictRepositoryCustom {
}
