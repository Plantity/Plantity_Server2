package com.plantity.server.controller;

import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlantController {
    private final PlantLogRepository plantLogRepository;


}
