package com.plantity.server.controller;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.service.KakaoService;
import com.plantity.server.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
//@RequiredArgsConstructor
public class UsersController {
    private final KakaoService kakaoService;
    private final UsersService usersService;
//    private final JwtTokenProvider jwtTokenProvider;


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


    @ResponseBody
    @GetMapping("/login/kakao") // access token 추출 확인 완
    public void kakaoCallback(@RequestParam String code) {
        //code 확인 완
        System.out.println("controller code :" + code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        System.out.println("controller access_token :" + access_Token);

        String userId = kakaoService.getUserId(access_Token);
        System.out.println(userId);
    }

    //로그인
    @PostMapping("/user/signin/social")
    public ResponseEntity<HashMap> kakaoLogin(@RequestBody UsersRequestDto.SocialSigninDto socialDto){

        HashMap<String, Object> responseMap = new HashMap<>();
        Users user = usersService.signIn(socialDto.getSocialType(), socialDto.getSocialToken());

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
        responseMap.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        responseMap.put("refresh_token", userService.issueRefreshToken(user));
        return new ResponseEntity<HashMap>(responseMap, HttpStatus.OK);
    }

    @PostMapping("/user/refresh")
    public ResponseEntity<HashMap> reissueToken(@RequestHeader(value="X-AUTH-TOKEN") String token,
                                                @RequestHeader(value="REFRESH-TOKEN") String refreshToken){

        Users user = usersService.reissueTokenByRefreshToken(token, refreshToken);
        HashMap<String, Object> responseMap = new HashMap<>();
        if (user != null) {
            responseMap.put("status", 200);
            responseMap.put("message", "토큰 재발급 성공");
//            responseMap.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
            return new ResponseEntity<HashMap>(responseMap, HttpStatus.OK);
        }
        else{
            responseMap.put("status", 401);
            responseMap.put("message", "잘못되었거나 만료된 refresh token 재로그인 필요");
            return new ResponseEntity<HashMap>(responseMap, HttpStatus.UNAUTHORIZED);
        }
    }
}
