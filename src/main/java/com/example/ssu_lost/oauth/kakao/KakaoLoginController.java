package com.example.ssu_lost.oauth.kakao;

import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback/kakao")
    public ApiResponse<?> callback(@RequestParam("code") String code) {

        String accessToken = kakaoService.getAccessTokenFromKakao(code);


        return ApiResponse.onSuccess(ResponseCode.OK, code);
    }
}
