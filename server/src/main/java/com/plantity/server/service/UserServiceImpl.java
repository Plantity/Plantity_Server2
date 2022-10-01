package com.plantity.server.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.plantity.server.config.JwtTokenProvider;
import com.plantity.server.domain.RefreshToken;
import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.repository.RefreshTokenRepository;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UsersService {
    private final UsersRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    /*
    @Transactional
    public Long updateUser(Long id, UsersRequestDto userRequestDto){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다")
        );

        user.updateUser(userRequestDto);
        return user.getUserId();
    }

     */

    public String getKakaoAccessToken (String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + KeyService.getRESTAPIKey()); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/login/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            System.out.println(conn.getResponseMessage());

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            //출력확인
            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    public String getUserId(String accessToken) {

        //조회한 accessToken으로 카카오 서버에 사용자 정보 조회를 요청함
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String id = null;
        String email = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken); // hearder 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonElement element = JsonParser.parseString(result);

            id = element.getAsJsonObject().get("id").getAsString();

            //email 받아오기
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }
            //id, Email 받아오기 성공
            System.out.println("id : " + id);
            System.out.println("email : " + email);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Users signUp(UsersRequestDto.SignupDto SignupDto) {
        String socialId = null;
        if(SignupDto.getSocialType().equals("kakao"))
            socialId = getUserId(SignupDto.getSocialToken());

        if(socialId == null)
            return null;

        UsersRequestDto userDto = new UsersRequestDto();
        userDto.setSocial(SignupDto.getSocial());
        userDto.setNickName(SignupDto.getNickName());

        return userRepository.save(new Users(userDto));
    }


    @Override
    public Users signIn(String socialType, String socialToken) {
        String socialId = null;
        if(socialType.equals("kakao"))
            socialId = getUserId(socialToken);

        if(socialId == null)
            return null;

        socialId = "kakao/" + socialId;
        Users user = userRepository.findById(socialId).orElseGet(()->null);
        return user;
    }


    @Override
    public Boolean isTokenValid(String accessToken){
        String reqURL = "https://kapi.kakao.com/v1/user/access_token_info";
        String id = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("결과 : " + result);
            JsonElement element = JsonParser.parseString(result);



        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Object giveRefreshToken(Users user) {
        return refreshTokenRepository.findByUserId(user.getUserId()).orElseGet(null).getRefreshToken();
    }

    @Override
    public Object issueRefreshToken(Users user) {
        RefreshToken refreshToken = new RefreshToken(user.getUserId(), jwtTokenProvider.createRefreshToken());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }

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
}
