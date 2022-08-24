package com.plantity.server.service;

import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.repository.MyPlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPlantService {

    private final MyPlantRepository myPlantRepository;

    @Transactional
    public Long save(MyPlantSaveRequestDto requestDto) {
        return myPlantRepository.save(requestDto.toEntity()).getMyPlantId();
    }
}
