package com.plantity.server.config;

import com.plantity.server.service.KeyService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("spring.jwt.secret")                     // application.properties에서 secretKey 주입
    private String secretKey = KeyService.getSecretKey();

    /* 토큰 유효시간 */
    private long tokenValidTime = 3 * 60 * 60 * 1000L;  // 3시간
    private long refreshTokenValidTime = 365 * 24 * 60 * 60 * 1000L; //1년
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
