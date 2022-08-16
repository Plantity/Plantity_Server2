package com.plantity.server.controller;

import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyPlantApiController {

    private final MyPlantService myPlantService;

    @PostMapping("/api/v1/myplants")
    public Long save(@RequestBody MyPlantSaveRequestDto requestDto) {

        return myPlantService.save(requestDto);
    }
}
