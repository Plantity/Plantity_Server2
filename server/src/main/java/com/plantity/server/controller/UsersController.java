package com.plantity.server.controller;

import com.plantity.server.config.BaseResponse2;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.domain.users.UsersResponseDto;
import com.plantity.server.dto.res.users.UserResponse;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.plantity.server.constants.SuccessCode.USER_INFO_SUCCESS;

@RestController
@AllArgsConstructor
//@RequiredArgsConstructor
public class UsersController {
//    private final UsersRepository userRepository;
    private final UsersService usersService;
//
//    // user 목록 조회
//    @GetMapping("/users")
//    public List<Users> getUsers(){
//        return userRepository.findAll();
//    }
//
//    // user 상세 정보 조회
//    @GetMapping("/users/{userId}")
//    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
//
//        UsersRequestDto usersRequestDto = UsersRequestDto.of(userId);
//
//        UsersResponseDto usersResponseDto = userService.userInfo(usersRequestDto);
//
//        return UserResponse.newResponse(USER_INFO_SUCCESS, usersResponseDto);
//    }
//
//    // 임시로 User 추가하기
//    @PostMapping("/save/test/users")
//    public BaseResponse2<Long> postTestUsers(@RequestBody UsersRequestDto usersRequestDto){
//        Users users = new Users(usersRequestDto);
//        userRepository.save(users);
//        return new BaseResponse2<>(users.getUserId());
//    }

//    카카오 callback
//    [GET] /oauth/kakao/callback
//    code로 accessToken 추출하기

    @ResponseBody
    @GetMapping("/login/kakao") // access token 추출 확인 완
    public void kakaoCallback(@RequestParam String code) {
        //code 확인 완
        System.out.println("controller code :" + code);
        String access_Token = usersService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        String userId = usersService.getUserId(access_Token);
        System.out.println(userId);
    }
}
