package com.example.ssu_lost.oauth.google.controller;

import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.response.ApiResponse;
import com.example.ssu_lost.oauth.google.dto.GoogleUserInfoDto;
import com.example.ssu_lost.oauth.google.service.GoogleService;
import com.example.ssu_lost.oauth.kakao.dto.KakaoUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class GoogleLoginController {

    private final GoogleService googleService;

    @GetMapping("/callback/google")
    public ApiResponse<?> callback(@RequestParam("code") String code) {
//        String accessToken = googleService.getAccessTokenFromGoogle(code);
        GoogleUserInfoDto googleUserInfoDto = googleService.getUserInfo(code);
        return ApiResponse.onSuccess(ResponseCode.OK, googleUserInfoDto);
    }
}
