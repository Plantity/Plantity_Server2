package com.plantity.server.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public interface UsersService {

    public String getKakaoAccessToken (String code);
    public String getUserId(String accessToken);
    public Users signUp(UsersRequestDto.SignupDto SignupDto);

    public Users signIn(String socialType, String socialToken);

    public Boolean isTokenValid(String accessToken);


    Object giveRefreshToken(Users user);

    Object issueRefreshToken(Users user);


}
