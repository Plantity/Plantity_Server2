package com.plantity.server.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.plantity.server.config.JwtTokenProvider;
import com.plantity.server.domain.RefreshToken;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.RefreshTokenRepository;
import com.plantity.server.repository.UsersRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;                              // 패스워드 인코더
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final KakaoService kakaoService;

//

    @Override
    public Users signUp(UsersRequestDto.SocialSignupDto socialSignupDto) {
        String socialId = null;
        if(socialSignupDto.getSocialType().equals("kakao"))
            socialId = kakaoService.getUserId(socialSignupDto.getSocialToken());

        if(socialId == null)
            return null;

        UsersRequestDto userDto = new UsersRequestDto();
        userDto.setSocial("kakao/" + socialId);
        userDto.setNickname(socialSignupDto.getNickname());

//        Tree tree = new Tree();

        return usersRepository.save(new Users(userDto));
    }



//    @Override
//    public String issueRefreshToken(Users user){
//        RefreshToken refreshToken = new RefreshToken(user.getId(), jwtTokenProvider.createRefreshToken());
//        refreshTokenRepository.save(refreshToken);
//        return refreshToken.getRefreshToken();
//    }
//
//    public String giveRefreshToken(Users user){
//        return refreshTokenRepository.findByUserId(user.getId()).orElseGet(null).getRefreshToken();
//    }

    @Override
    public Users reissueTokenByRefreshToken(String token, String refreshToken){

        if(!jwtTokenProvider.validateTokenExceptExpiration(token)){
            return null;
        }

        if(!jwtTokenProvider.validateToken(refreshToken)){
            return null;
        }

        RefreshToken refreshTokenObject = refreshTokenRepository.findByRefreshToken(refreshToken).orElseGet(null);
        if(refreshTokenObject == null){
            return null;
        }

        Users user = usersRepository.findById(refreshTokenObject.getUserId()).orElseGet(null);
        if(user == null){
            return null;
        }
        return user;
    }


    @Override
    public Users signIn(String socialType, String socialToken) {
        String socialId = null;
        if(socialType.equals("kakao"))
            socialId = kakaoService.getUserId(socialToken);

        if(socialId == null)
            return null;

        socialId = "kakao/" + socialId;
        Users user = usersRepository.findBySocialId(socialId).orElseGet(()->null);
        return user;
    }


    public Boolean checkIsMember(String socialId){
        return usersRepository.existsBySocialId("kakao/" + socialId);
    }

    @Override
    public void updateProfile(Users user, String nickname){

        user.updateProfile(nickname);
        usersRepository.save(user);
    }


    @Override
    public Boolean validateDuplicateEmail(String email){
        return usersRepository.existsByEmail(email);
    }

    @Override
    public Boolean validateDuplicateNickname(String nickname){
        return usersRepository.existsByNickname(nickname);
    }

    @Override
    public Users findUserByEmail(String email){
        return usersRepository.findByEmail(email).orElseGet(()->null);
    }

    @Override
    public void saveUpdatedUser(Users user){
        usersRepository.save(user);
    }
}

