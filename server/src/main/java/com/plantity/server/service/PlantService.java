package com.plantity.server.service;

import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlantService {
    private final PlantLogRepository plantLogRepository;
}
