package com.plantity.server.service;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantRequestDto;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import com.plantity.server.domain.plantlog.DateMyPlantLogResponseDto;
import com.plantity.server.domain.plantlog.MyPlantLogRequestDto;
import com.plantity.server.domain.plantlog.MyPlantLogResponseDto;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.users.Users;
import com.plantity.server.dto.req.DateMyPlantLogRequestDto;
import com.plantity.server.dto.res.plantlog.DatePlantLogResponse;
import com.plantity.server.repository.MyPlantRepository;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPlantService {

    private final MyPlantRepository myPlantRepository;
    private final PlantLogRepository plantLogRepository;
    private final UsersRepository usersRepository;

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
    public DateMyPlantLogResponseDto datePlantLog(DateMyPlantLogRequestDto requestDto) {
        PlantLog plantLog = plantLogRepository.findById(requestDto.getPlantId()).orElseThrow(
                () -> new IllegalArgumentException("해당 날짜에 로그가 없습니다.")
        );

        return DateMyPlantLogResponseDto.from(plantLog);
    }

    // 광합성
    @Transactional
    public Long updateSun(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateSun(true);

        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        users.updateSun();
        return plantLog.getPlantId();
    }

    // 분갈이
    @Transactional
    public Long updateRepot(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다")
        );
        plantLog.updateRepot(true);

        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        users.updateRepot();

        return plantLog.getPlantId();
    }

    // 물 주기
    @Transactional
    public Long updateWater(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateWater(true);

        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        users.updateWater();

        return plantLog.getPlantId();
    }

    // 관찰
    @Transactional
    public Long updateLook(Long userId, Long myPlantId) {
        PlantLog plantLog = plantLogRepository.findById(myPlantId).orElseThrow(
                () -> new IllegalArgumentException("해당 식물이 없습니다.")
        );
        plantLog.updateLook(true);

        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        users.updateLook();

        return plantLog.getPlantId();
    }
}