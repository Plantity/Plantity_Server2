package com.plantity.server.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.plantity.server.config.JwtTokenProvider;
import com.plantity.server.domain.RefreshToken;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.RefreshTokenRepository;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Collections;
import java.util.Optional;

import static com.plantity.server.utils.ErrorCode.MEMBER_NOT_FOUND;


@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    @Autowired
    private final UsersRepository userRepository;
    private final KakaoService kakaoService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;


//    @Override
//    public Users signUp(String social_type, String social_token, String nickname) {
//        String id = null;
//        String email = null;
//        if(social_type.equals("kakao"))
//            id = getUserId(social_token);
//
//        if(id == null)
//            return null;
//
//        UsersRequestDto userDto = new UsersRequestDto();
//        userDto.setSocial(social_type);
//        userDto.setUserId(id);
//        userDto.setNickName(nickname);
//
//
//        return userRepository.save(new Users(userDto));
//    }


//    @Override
//    public Users signIn(String socialType, String socialToken) {
//        String socialId = null;
//        if(socialType.equals("kakao"))
//            socialId = getUserId(socialToken);
//
//        if(socialId == null)
//            return null;
//
//        socialId = "kakao/" + socialId;
//        Users user = userRepository.findById(socialId).orElseGet(()->null);
//        return user;
//    }


//    public Boolean isTokenValid(String accessToken){
//        String reqURL = "https://kapi.kakao.com/v1/user/access_token_info";
//        String id = null;
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            System.out.println("결과 : " + result);
//            JsonElement element = JsonParser.parseString(result);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    public Object giveRefreshToken(Users user) {
//        return refreshTokenRepository.findByUserId(user.getUserId()).orElseGet(null).getRefreshToken();
//    }
//
//    public Object issueRefreshToken(Users user) {
//        RefreshToken refreshToken = new RefreshToken(user.getUserId(), jwtTokenProvider.createRefreshToken());
//        refreshTokenRepository.save(refreshToken);
//        return refreshToken.getRefreshToken();
//    }

//    @Override
//    Object issueRefreshToken(Users user){
//        RefreshToken refreshToken = new RefreshToken(user.getId(), jwtTokenProvider.createRefreshToken());
//        refreshTokenRepository.save(refreshToken);
//        return refreshToken.getRefreshToken();
//    }


//    @Override
//    Object giveRefreshToken(Users user){
//        return refreshTokenRepository.findByUserId(user.getId()).orElseGet(null).getRefreshToken();
//    }


    public Users signUp(String social_type, String social_token, String nickname) throws ParseException {

        String id = null;

        if (social_type.equals("kakao")) {
            id = kakaoService.getUserId(social_token);
        }

        UsersRequestDto userDto = new UsersRequestDto();
        userDto.setUserId(id);
        userDto.setSocialType(social_type);
        userDto.setNickname(nickname);


        return userRepository.save(new Users(userDto));
    }

    public Users signIn(String socialType, String socialToken) throws ParseException {
        String id = null;

        if (socialType.equals("kakao")) {
            id = kakaoService.getUserId(socialToken);
        }

       Users user = findUserBySocialTypeAndUserId(socialType, id);
        return user;
    }


    private Users findUserBySocialTypeAndUserId(String socialType, String socialId) {
        return userRepository.findBySocialTypeAndUserId(socialType, socialId)
                .orElseThrow(() -> new NotFoundException("MEMBER_NOT_FOUND"));
    }
//
//    public Users findUserByUserId(String userId) {
//        return userRepository.findByUserId(userId).orElseGet(() -> null);
//    }




}


