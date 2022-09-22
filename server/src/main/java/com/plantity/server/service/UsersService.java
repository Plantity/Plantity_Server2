package com.plantity.server.service;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    //회원가입
     public Users signUp(UsersRequestDto.SocialSignupDto socialSignupDto);

    // 로그인할 이메일 & 패스워드 체크
    public Users signIn(String socialType, String socialToken);


//     public String issueRefreshToken(Users user);

//    public String giveRefreshToken(Users user);

//    public Users reissueTokenByRefreshToken(String token, String refreshToken);

    public Boolean validateDuplicateEmail(String email);

    public Boolean validateDuplicateNickname(String nickname);
    public Boolean checkIsMember(String socialId);
    public Users findUserByEmail(String email);

}
