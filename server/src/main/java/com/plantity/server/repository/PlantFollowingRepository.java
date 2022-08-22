package com.plantity.server.repository;

import com.plantity.server.domain.Plant.Plant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

public interface PlantFollowingRepository extends JpaRepository<PlantFollowing, Long> {

//    Optional<PlantFollowing> findFollowByUserAndPlantId(Users users, Plant plantIdx);


}
