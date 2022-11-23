package com.plantity.server.controller;

import com.plantity.server.config.BaseResponse2;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.KakaoService;
import com.plantity.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository userRepository;
    private final UserService usersService;
    private final KakaoService kakaoService;

    @ResponseBody
    @GetMapping("/login/kakao")
    public void kakaoCallback(@RequestParam String code) {

        // kakao 로그인시 발급되는 code
        // usersService의 getKakaoAccessToken(code)에 인수로 전달 -> accessToken 발급
        System.out.println("controller code :" + code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        // getUserID : 발급받은 accessToken으로 kakao에서 유저정보 조회
        // 토큰으로 조회한 user id를 출력함
        String userId = kakaoService.getUserId(access_Token);
        System.out.println(userId);
    }

    @PostMapping("/users/signin")
    public BaseResponse2<Users> socialLogin(@RequestBody UsersRequestDto.SigninDto loginDto) throws ParseException {

        Users user = usersService.signIn(loginDto.getSocialType(), loginDto.getSocialToken());

        String socialType = loginDto.getSocialType();
        System.out.println(socialType);
        return new BaseResponse2<Users>(user);
    }

    @PostMapping("/users/signup")
    public BaseResponse2<Object> socialSignUp(@RequestBody UsersRequestDto.SignUpDto signUpDto) throws ParseException {

        Users user = usersService.signUp(signUpDto.getSocialType(), signUpDto.getSocialToken(), signUpDto.getNickname());

        return new BaseResponse2<Object>(user);
    }
}
