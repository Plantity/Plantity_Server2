package com.plantity.server.service;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.plantlog.PlantLogUpdateRequestDto;
import com.plantity.server.repository.MyPlantRepository;
import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPlantService {

    private final MyPlantRepository myPlantRepository;
    private final PlantLogRepository plantLogRepository;

    @Transactional
    public Long save(MyPlantSaveRequestDto requestDto) {
        return myPlantRepository.save(requestDto.toEntity()).getMyPlantId();
    }

    @Transactional
    public Long updateSun(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateSun(true);
        return plantLog.getPlantId();
    }
    
    @Transactional
    public Long updateRepot(Long userId, Long myPlantId){
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다")
        );
        plantLog.updateRepot(true);
        return plantLog.getPlantId();
    }
}
