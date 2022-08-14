package com.plantity.server.controller;

import com.plantity.server.domain.PlantFollowingDto;
import com.plantity.server.service.PlantFollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plantFollowing")
public class PlantFollowingController {

    private final PlantFollowingService plantFollowingService;

    @PostMapping
    public ResponseEntity<PlantFollowingDto> plantFollowing(@RequestBody PlantFollowingDto plantFollowingDto) throws IOException {
        plantFollowingService.plantFollowing(plantFollowingDto);
        return new ResponseEntity<>(plantFollowingDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<PlantFollowingDto> plantUnfollowing(@RequestBody PlantFollowingDto plantFollowingDto) throws IOException {
        plantFollowingService.plantUnfollowing(plantFollowingDto);
        return new ResponseEntity<>(plantFollowingDto, HttpStatus.OK);
    }
}
