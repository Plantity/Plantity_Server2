package com.plantity.server.controller;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersRepository userRepository;
    private final UsersService usersService;

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
//        UsersResponseDto usersResponseDto = usersService.userInfo(usersRequestDto);
//
//        return UserResponse.newResponse(USER_INFO_SUCCESS, usersResponseDto);
//    }
//


    @ResponseBody
    @GetMapping("/login/kakao")
    public void kakaoCallback(@RequestParam String code) {

        // kakao 로그인시 발급되는 code
        // usersService의 getKakaoAccessToken(code)에 인수로 전달 -> accessToken 발급
        System.out.println("controller code :" + code);
        String access_Token = usersService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        // getUserID : 발급받은 accessToken으로 kakao에서 유저정보 조회
        // 토큰으로 조회한 user id를 출력함
        String userId = usersService.getUserId(access_Token);
        System.out.println(userId);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<HashMap> kakaoJoin(@RequestBody UsersRequestDto.SignupDto socialDto) {
        HashMap<String, Object> responseMap = new HashMap<>();

        if(!usersService.isTokenValid(socialDto.getSocialToken())){
            responseMap.put("status", 401);
            responseMap.put("message", "유효하지 않은 토큰");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        Users user = usersService.signUp(socialDto);

        responseMap.put("status", 200);
        responseMap.put("message", "회원가입 성공");
//        responseMap.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        responseMap.put("refresh_token", usersService.issueRefreshToken(user));
        return new ResponseEntity<HashMap>(responseMap, HttpStatus.OK);
    }


    @PostMapping("/user/signin")
    public ResponseEntity<HashMap> kakaoLogin(@RequestBody UsersRequestDto.SigninDto socialDto){

        HashMap<String, Object> responseMap = new HashMap<>();
        Users user = usersService.signIn(socialDto.getSocialType(), socialDto.getSocialToken());

        if(!usersService.isTokenValid(socialDto.getSocialToken())){
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
                responseMap.put("refresh_token", usersService.giveRefreshToken(user));
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            }
        }
        else {
            responseMap.put("status", 401);
            responseMap.put("message", "소셜 타입 오류");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }
    }

//    조회한 내용을 userRepository에 저장
//    임시로 User 추가하기
//    @PostMapping("/save/test/users")
//    public BaseResponse2<Long> postTestUsers(@RequestBody UsersRequestDto usersRequestDto){
//        Users users = new Users(usersRequestDto);
//        userRepository.save(users);
//        return new BaseResponse2<>(users.getUserId());
//    }
}
