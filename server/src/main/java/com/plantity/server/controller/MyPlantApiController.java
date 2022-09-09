package com.plantity.server.controller;

import static com.plantity.server.constants.SuccessCode.*;

import com.plantity.server.config.BaseResponse2;
import com.plantity.server.constants.ExceptionCode;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.dto.res.myplant.MyPlantSaveResponse;
import com.plantity.server.dto.res.myplant.MyPlantUpdateResponse;
import com.plantity.server.exception.CustomException;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/myplant")
public class MyPlantApiController {

    private final MyPlantService myPlantService;
    private final PlantLogRepository plantLogRepository;

    @PostMapping("/save")
    public ResponseEntity<MyPlantSaveResponse> save(@RequestBody MyPlantSaveRequestDto requestDto) {

        if (requestDto.getPlantName() == null | requestDto.getPlantType() == null) {
            throw new CustomException(ExceptionCode.NO_REQUIRED_PARAMETER);
        }

        myPlantService.save(requestDto);

        return MyPlantSaveResponse.newResponse(CREATE_MYPLANT_SUCCESS);
    }

    @PutMapping("/sun/{userId}/{myPlantId}")
    public ResponseEntity<MyPlantUpdateResponse> putSun(@PathVariable Long userId, @PathVariable Long myPlantId) {
        myPlantService.updateSun(userId, myPlantId);
        return MyPlantUpdateResponse.newResponse(UPDATE_SUNPLANTLOG_SUCCESS);
    }

    @PutMapping("/repot/{userId}/{myPlantId}")
    public BaseResponse2<String> putRepot(@PathVariable Long userId, @PathVariable Long myPlantId){
        myPlantService.updateRepot(userId, myPlantId);
        return new BaseResponse2("success");
    }

    @GetMapping("/plant")
    public List<PlantLog> check(){
        return plantLogRepository.findAll();
    }
}
