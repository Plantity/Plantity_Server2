package com.plantity.server.repository;

import com.plantity.server.domain.plant.list.PlantList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantListRepository extends JpaRepository<PlantList, Long> {
}
