package com.plantity.server.service;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.domain.users.UsersResponseDto;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository userRepository;



//    public UsersResponseDto userInfo(UsersRequestDto requestDto) {
//        Users users = userRepository.findById(requestDto.getUserId()).orElseThrow(
//                () -> new IllegalArgumentException("해당 유저가 없습니다.")
//        );
//
//        return UsersResponseDto.from(users);
//
//    }



//    public String getKakaoAccessToken(String code) {
//        String access_Token = "";
//        String refresh_Token = "";
//        String reqURL = "https://kauth.kakao.com/oauth/token";
//
//        try {
//            URL url = new URL(reqURL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//            StringBuilder sb = new StringBuilder();
//            sb.append("grant_type=authorization_code");
//            sb.append("&client_id=538dc8c76ce7fafe94d53adbf71b98db");
//            sb.append("&redirect_uri=http://localhost:8080/login/kakao");
//            sb.append("&code=" + code);
//            bw.write(sb.toString());
//            bw.flush();
//
//            int responseCode = conn.getResponseCode();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line = "";
//            String result = "";
//
//            while ((line = br.readLine()) != null) {
//                result += line;
//            }
//
//            JsonElement element = JsonParser.parseString(result);
//
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//            br.close();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return access_Token;
//    }

    private final UsersRepository usersRepository;
    private final KakaoService kakaoService;

    @Override
    public Users signUp(UsersRequestDto.SocialSignupDto socialSignupDto){
        String userId = null;
        if(socialSignupDto.getSocialType().equals("kakao"))
            userId = kakaoService.getUserId(socialSignupDto.getSocialToken());

        if(userId == null)
            return null;

        UsersRequestDto usersRequestDto = new UsersRequestDto();
        usersRequestDto.setUserId("kakao/" + userId);
        usersRequestDto.setSocial(socialSignupDto.getSocial());
        usersRequestDto.setNickName(socialSignupDto.getNickName());


        return userRepository.save(new Users(usersRequestDto));
    }

    /*
    @Override
    public String issueRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken(user.getId(), jwtTokenProvider.createRefreshToken());
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }

    public String giveRefreshToken(User user){
        return refreshTokenRepository.findByUserId(user.getId()).orElseGet(null).getRefreshToken();
    }

    @Override
    public User reissueTokenByRefreshToken(String token, String refreshToken){

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

        User user = userRepository.findById(refreshTokenObject.getUserId()).orElseGet(null);
        if(user == null){
            return null;
        }
        return user;
    }
*/

    @Override
    public Users signIn(String socialType, String socialToken) {
        String socialId = null;
        if(socialType.equals("kakao"))
            socialId = kakaoService.getUserId(socialToken);

        if(socialId == null)
            return null;

        socialId = "kakao/" + socialId;
        Users user = usersRepository.findByUserId(socialId).orElseGet(()->null);
        return user;
    }

    public Boolean checkIsMember(String socialId){
        return usersRepository.existsByUserId("kakao/" + socialId);
    }

    @Override
    public Boolean validateDuplicateEmail(String email){
        return usersRepository.existsBySocial(email);
    }

    @Override
    public Boolean validateDuplicateNickname(String nickname){
        return userRepository.existsByNickName(nickname);
    }

    @Override
    public Users findUserByEmail(String social){
        return userRepository.findBySocial(social).orElseGet(()->null);
    }

}
