package com.plantity.server.repository;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantFollowingRepository extends JpaRepository<Users, Long> {
    /*
    void save(PlantFollowing plantFollowing);

    void delete(PlantFollowing plantFollowing);

    Optional<PlantFollowing> findFollowByUserAndPlantId(Users users, Long plantId);

     */
}
