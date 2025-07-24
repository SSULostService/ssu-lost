package com.example.ssu_lost.oauth.kakao.controller;

import com.example.ssu_lost.global.code.ResponseCode;
import com.example.ssu_lost.global.response.ApiResponse;
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
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/callback/kakao")
    public ApiResponse<KakaoUserInfoDto> callback(@RequestParam("code") String code) {

        //String accessToken = kakaoService.getAccessTokenFromKakao(code);

        KakaoUserInfoDto kakaoUserInfoDto = kakaoService.getUserInfo(code);

        return ApiResponse.onSuccess(ResponseCode.OK, kakaoUserInfoDto);
    }
}
