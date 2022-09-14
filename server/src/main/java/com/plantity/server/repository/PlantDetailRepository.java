package com.plantity.server.repository;

import com.plantity.server.domain.plant.detail.PlantDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantDetailRepository extends JpaRepository<PlantDetail, Integer> {
    Page<PlantDetail> findAll(Pageable pageable);
    PlantDetail findByCntntsNo(String cntntsNo);
}
