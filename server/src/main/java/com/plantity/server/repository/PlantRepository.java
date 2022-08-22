package com.plantity.server.repository;

import com.plantity.server.domain.Plant.Plant;
import com.plantity.server.domain.plantlog.PlantLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
