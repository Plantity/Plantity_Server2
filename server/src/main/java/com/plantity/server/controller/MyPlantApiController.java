package com.plantity.server.controller;

import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
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
    public Long save(@RequestBody MyPlantSaveRequestDto requestDto) {

        myPlantService.save(requestDto);

        //성공 메세지

        //에러 메세지

        return;
    }
}
