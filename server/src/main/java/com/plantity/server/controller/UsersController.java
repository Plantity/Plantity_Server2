package com.plantity.server.controller;

import com.plantity.server.config.JwtTokenProvider;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.KakaoService;
import com.plantity.server.service.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository usersRepository;
    private final UsersServiceImpl usersServiceService;

    @Autowired
    UsersServiceImpl us;

    // user 목록 조회
    @GetMapping("/users")
    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    private final UsersServiceImpl usersService;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoService kakaoService;

    /* 새로생성 start*/
    @GetMapping("/login/kakao")
    public void  kakaoCallback(@RequestParam String code) {
        System.out.println("controller code :" + code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        String userId = kakaoService.getUserId(access_Token);
        System.out.println(userId);
    }

    @PostMapping("/user/signin/social")
    public ResponseEntity<HashMap> kakaoLogin(@RequestBody UsersRequestDto.SocialSigninDto socialDto){

        HashMap<String, Object> responseMap = new HashMap<>();
        Users user = usersServiceService.signIn(socialDto.getSocialType(), socialDto.getSocialToken());

        if(!kakaoService.isTokenValid(socialDto.getSocialToken())){
            responseMap.put("status", 401);
            responseMap.put("message", "유효하지 않은 토큰");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        if (socialDto.getSocialType().equals("kakao")) {
            if (user == null) {
                responseMap.put("status", 404);
                responseMap.put("message", "회원 정보 없음");
                return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
            }
            else {
                responseMap.put("status", 200);
                responseMap.put("message", "로그인 성공");
//                responseMap.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
//                responseMap.put("refresh_token", usersService.giveRefreshToken(user));
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            }
        }
        else {
            responseMap.put("status", 401);
            responseMap.put("message", "소셜 타입 오류");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/signup/social")
    public ResponseEntity<HashMap> kakaoJoin(@RequestBody UsersRequestDto.SocialSignupDto socialDto) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(!kakaoService.isTokenValid(socialDto.getSocialToken())){
            responseMap.put("status", 401);
            responseMap.put("message", "유효하지 않은 토큰");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        Users user = usersService.signUp(socialDto);

        responseMap.put("status", 200);
        responseMap.put("message", "회원가입 성공");
//        responseMap.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
//        responseMap.put("refresh_token", usersService.issueRefreshToken(user));
        return new ResponseEntity<HashMap>(responseMap, HttpStatus.OK);
    }



}
