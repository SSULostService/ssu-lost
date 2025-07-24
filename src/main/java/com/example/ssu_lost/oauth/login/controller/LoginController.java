package com.example.ssu_lost.oauth.login.controller;

import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.response.ApiResponse;
import com.example.ssu_lost.oauth.OAuthUserInfo;
import com.example.ssu_lost.oauth.google.dto.GoogleUserInfoDto;
import com.example.ssu_lost.oauth.google.service.GoogleService;
import com.example.ssu_lost.oauth.kakao.dto.KakaoUserInfoDto;
import com.example.ssu_lost.oauth.kakao.service.KakaoService;
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
public class LoginController {

    private final KakaoService kakaoService;
    private final GoogleService googleService;

    @GetMapping("/callback/kakao")
    public ApiResponse<KakaoUserInfoDto> callbackKakao(@RequestParam("code") String code) {
        KakaoUserInfoDto kakaoUserInfoDto = kakaoService.getUserInfo(code);

        return ApiResponse.onSuccess(ResponseCode.OK, kakaoUserInfoDto);
    }

    @GetMapping("/callback/google")
    public ApiResponse<OAuthUserInfo> callbackGoogle(@RequestParam("code") String code) {
        OAuthUserInfo oAuthUserInfo = googleService.getUserInfo(code);

        return ApiResponse.onSuccess(ResponseCode.OK, oAuthUserInfo);
    }
}
