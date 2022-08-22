package com.plantity.server.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {


//     * 카카오 callback
//     * [GET] /kakao/oauth
// 실제로는 프론트에서 백으로 전달해 줘야 되는 부분
// 로그인시 생성되는 코드 파싱하는 부분


    @ResponseBody
    @GetMapping("/oauth")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
    }
}