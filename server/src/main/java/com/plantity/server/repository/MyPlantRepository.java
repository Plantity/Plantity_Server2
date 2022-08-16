package com.plantity.server.repository;

import com.plantity.server.domain.myPlant.MyPlant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPlantRepository extends JpaRepository<MyPlant, Long> {
}
