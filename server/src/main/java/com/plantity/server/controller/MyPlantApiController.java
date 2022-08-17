package com.plantity.server.controller;

import static com.plantity.server.constants.SuccessCode.*;

import com.plantity.server.constants.ExceptionCode;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.dto.res.myplant.MyPlantSaveResponse;
import com.plantity.server.exception.CustomException;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/myplant")
public class MyPlantApiController {

    private final MyPlantService myPlantService;

    @PostMapping("/save")
    public ResponseEntity<MyPlantSaveResponse> save(@RequestBody MyPlantSaveRequestDto requestDto) {

        if (requestDto.getPlantName() == null | requestDto.getPlantType() == null | requestDto.getLevel() == 0) {
            throw new CustomException(ExceptionCode.NO_REQUIRED_PARAMETER);
        }

        myPlantService.save(requestDto);

        return MyPlantSaveResponse.newResponse(CREATE_MYPLANT_SUCCESS);
    }
}
