package com.plantity.server.service;

import com.plantity.server.domain.plant.list.PlantList;
import com.plantity.server.repository.PlantListRepository;
import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlantService {
    private final PlantLogRepository plantLogRepository;
    private final PlantListRepository plantListRepository;


    public PlantList getPlantListById(int plantListId){
        return plantListRepository.findById(plantListId);
    }
}
