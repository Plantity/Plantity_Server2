package com.plantity.server.controller;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantFollowing.PlantFollowingDto;
import com.plantity.server.service.PlantFollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plantFollowing")
public class PlantFollowingController {


    private final PlantFollowingService plantFollowingService;

//    //북마크
//    @PostMapping
//    public ResponseEntity<PlantFollowingDto> follow(@RequestBody PlantFollowingDto heartDto) {
//        PlantFollowingService.follow(heartDto);
//        return new ResponseEntity<>(heartDto, HttpStatus.CREATED);
//    }
//
//    //북마크취소
//    @DeleteMapping
//    public ResponseEntity<PlantFollowingDto> unFollow(@RequestBody PlantFollowingDto plantFollowingDto) throws IOException {
//        plantFollowingService.unFollow(plantFollowingDto);
//        return new ResponseEntity<>(plantFollowingDto, HttpStatus.OK);
//    }


}
