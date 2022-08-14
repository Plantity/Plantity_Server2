package com.plantity.server.repository;

import com.plantity.server.domain.PlantFollowing;
import com.plantity.server.domain.Users;

import java.util.Optional;

public interface PlantFollowingRepository {
    void save(PlantFollowing plantFollowing);

    void delete(PlantFollowing plantFollowing);

    Optional<PlantFollowing> findFollowByUserAndPlantId(Users users, Long plantId);
}
