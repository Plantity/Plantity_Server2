package com.plantity.server.service;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantRequestDto;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import com.plantity.server.domain.plantlog.MyPlantLogRequestDto;
import com.plantity.server.domain.plantlog.MyPlantLogResponseDto;
import com.plantity.server.domain.plantlog.PlantLog;
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

    /*
    @Transactional
    public Long save(MyPlantSaveRequestDto requestDto) {
        return myPlantRepository.save(requestDto.toEntity()).getMyPlantId();
    }

     */

    public MyPlantResponseDto myPlantInfo(MyPlantRequestDto requestDto) {
        MyPlant myPlant = myPlantRepository.findById(requestDto.getMyPlantId()).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );

        return MyPlantResponseDto.from(myPlant);
    }

    @Transactional
    public MyPlantLogResponseDto plantLogInfo(MyPlantLogRequestDto requestDto) {
        PlantLog plantLog = plantLogRepository.findById(requestDto.getMyPlantLogId()).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );

        return MyPlantLogResponseDto.from(plantLog);
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
    public Long updateRepot(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다")
        );
        plantLog.updateRepot(true);
        return plantLog.getPlantId();
    }

    @Transactional
    public Long updateWater(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateWater(true);
        return plantLog.getPlantId();
    }

    @Transactional
    public Long updateLook(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateLook(true);
        return plantLog.getPlantId();
    }
}