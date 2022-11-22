package com.plantity.server.repository;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantDetailRes;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPlantRepository extends JpaRepository<MyPlant, Long> {
    List<MyPlantResponseDto> findAllByUserIdx(Long userId);
    MyPlantDetailRes findByMyPlantId(Long myPlantId);
    Boolean existsByMyPlantId(Long myPlantId);
}
