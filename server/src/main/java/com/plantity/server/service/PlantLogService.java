package com.plantity.server.service;

import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.plantlog.PlantLogSaveRequestDto;
import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PlantLogService {

    private final PlantLogRepository plantLogRepository;

    @Transactional
    public Long save(PlantLogSaveRequestDto requestDto) {
        return plantLogRepository.save(requestDto.toEntity()).getPlantId();
    }

    @Transactional
    public Long sunUpdate(Long id, PlantLogSaveRequestDto requestDto) {
        PlantLog plantLogs = plantLogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("로그를 업데이트할 식물이 없습니다. id=" + id));

        boolean sunupdate = requestDto.getLog().isSun();

        plantLogs.update(sunupdate);

        return id;
    }
}
