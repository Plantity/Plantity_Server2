package com.plantity.server.service;


import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantFollowing.PlantFollowingDto;
import com.plantity.server.domain.Users;
import com.plantity.server.repository.PlantFollowingRepository;
import com.plantity.server.repository.PlantRepository;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlantFollowingService {

    private final PlantFollowingRepository plantFollowingRepository;
    private final UsersRepository usersRepository;
    private final PlantRepository plantRepository;
//    private final Provider jwtTokenProvider;JwtToken;

    private Users users;

    public void plantFollowing(PlantFollowingDto plantFollowingDto) throws IOException {
//        validateToken(plantFollowingDto, jwtToken);

        // 이미 북마크 한 식물일 경우 409 에러



        // 북마크한 식물 가져오기
//        PlantFollowing plantFollowing = PlantFollowing.build()
//                .plantId()
//                .user(usersRepository.findUserById(PlantFollowingDto.getUserId()).get())
//                .build();

//        plantFollowingRepository.save(plantFollowing);

    }

    public void plantUnfollowing(PlantFollowingDto plantFollowingDto) throws IOException {
//        validateToken(plantFollowingDto, jwtToken);

        Optional<PlantFollowing> plantFollowingOpt = findFollowWithUserAndPlantId(plantFollowingDto);


        plantFollowingRepository.delete(plantFollowingOpt.get());

    }

    public Optional<PlantFollowing> findFollowWithUserAndPlantId(PlantFollowingDto plantFollowingDto) {
        return plantFollowingRepository.findFollowByUserAndPlantId(users, plantFollowingDto.getPlantId());
    }


//    public ResponseEntity<PlantFollowingDto> plantFollowing(@RequestBody PlantFollowingDto plantFollowingDto) {
//        plantFollowingService.plantFollowing(plantFollowingDto);
//        return new ResponseEntity<>(plantFollowingDto, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<PlantFollowingDto> plantUnfollowing(@RequestBody PlantFollowingDto plantFollowingDto) {
//        plantFollowingService.plantUnfollow(plantFollowingDto);
//        return new ResponseEntity<>(plantFollowingDto, HttpStatus.OK);
//    }
}
