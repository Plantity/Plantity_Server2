package com.plantity.server.service;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    public String getKakaoAccessToken (String code);
    public String getUserId(String accessToken);
    public Users signUp(UsersRequestDto.SignupDto SignupDto);

    public Users signIn(String socialType, String socialToken);
}
