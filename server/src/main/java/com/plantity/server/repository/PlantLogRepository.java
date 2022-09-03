package com.plantity.server.repository;

import com.plantity.server.domain.plantlog.PlantLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantLogRepository extends JpaRepository<PlantLog, Long> {


}
