package com.plantity.server.repository;

import com.plantity.server.domain.plant.detail.PlantDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDetailRepository extends JpaRepository<PlantDetail, Long> {
}
