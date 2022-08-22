package com.plantity.server.repository;

import com.plantity.server.domain.plant.list.PlantList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantListRepository extends JpaRepository<PlantList, Integer> {

    PlantList findById(int plantListId);
}
