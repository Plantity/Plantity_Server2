package com.plantity.server.controller;

import com.plantity.server.config.BaseResponse2;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import com.plantity.server.domain.users.UserDto;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.domain.users.UsersResponseDto;
import com.plantity.server.dto.res.users.UserResponse;
import com.plantity.server.repository.MyPlantRepository;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.plantity.server.constants.SuccessCode.USER_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository userRepository;
    private final MyPlantRepository myPlantRepository;
    private final UsersService userService;

    // user 목록 조회
    @GetMapping("/users")
    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    // user 상세 정보 조회
    @GetMapping("/users/{userId}")
    public BaseResponse2<UserDto> getUser(@PathVariable Long userId) {
        UsersResponseDto usersResponseDto = UsersResponseDto.from(userRepository.findByUserId(userId)); // 유저 정보
        List<MyPlantResponseDto> myPlantResponseDtos = myPlantRepository.findAllByUserIdx(userId); // 내 식물 리스트

        return new BaseResponse2<>(new UserDto(usersResponseDto, myPlantResponseDtos));
    }

    // 임시로 User 추가하기
    @PostMapping("/save/test/users")
    public BaseResponse2<Long> postTestUsers(@RequestBody UsersRequestDto usersRequestDto){
        Users users = new Users(usersRequestDto);
        userRepository.save(users);
        return new BaseResponse2<>(users.getUserId());
    }
}
