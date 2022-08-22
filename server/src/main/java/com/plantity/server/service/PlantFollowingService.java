package com.plantity.server.service;


import com.plantity.server.domain.plantFollowing.PlantFollowingDto;
import com.plantity.server.domain.users.Users;
import com.plantity.server.repository.PlantFollowingRepository;
import com.plantity.server.repository.PlantRepository;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class PlantFollowingService {
//    private final PlantFollowingRepository plantFollowingRepository;
//    private final UsersRepository usersRepository;
//    private final PlantRepository plantRepository;
////    private final JwtTokenProvider jwtTokrnProvider;
//    private Users users;
//
//    public void follow(PlantFollowingDto plantFollowingDto, String jwtToken) throws IOException {
//        validateToken(PlantFollowingDto, jwtToken);
//
//        // 이미 좋아요 된 캠페인일 경우 409 에러
//        if (findHeartWithUserAndCampaignId(heartDto).isPresent()) {
//            throw new CustomException(ALREADY_HEARTED);
//        }
//
//        Heart heart = Heart.builder()
//                .campaignId(heartDto.getCampaignId())
//                .user(userRepository.findUserById(heartDto.getUserId()).get())
//                .build();
//        heartRepository.save(heart);
//
//        updateHeartCount(heartDto.getCampaignId(), 1);
//
//    }
//
//    public void unFollow(PlantFollowingDto plantFollowingDto) {
//
//        validateToken(heartDto, jwtToken);
//
//        Optional<Heart> heartOpt = findHeartWithUserAndCampaignId(heartDto);
//
//        if (heartOpt.isEmpty()) {
//            throw new CustomException(HEART_NOT_FOUND);
//        }
//
//        heartRepository.delete(heartOpt.get());
//
//        updateHeartCount(heartDto.getCampaignId(), -1);
//    }
//
//    public void validateToken(HeartDto heartDto, String jwtToken) {
//        // 생략 ... 유효한 토큰인지 검증하는 부분
//    }
//
//    public Optional<Heart> findHeartWithUserAndCampaignId(HeartDto heartDto) {
//        return heartRepository
//                .findHeartByUserAndCampaignId(user, heartDto.getCampaignId());
//    }
//
//    public void updateHeartCount(String campaignId, Integer plusOrMinus) throws IOException {
//        // Elasticsearch 업데이트 하는 부분
//        // 필요한 분들이 별로 없을것같아 생략합니다. 필요하시면 맨아래 깃헙 링크 참고하세요!
//        // MySQL만으로 구현하실때에는 JPA로 업데이트 하는 코드 넣어주세요~!
//    }



}

    /*
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




     */
