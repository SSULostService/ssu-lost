package com.example.ssu_lost.oauth.kakao.service;

import com.example.ssu_lost.oauth.kakao.dto.KakaoTokenDto;
import com.example.ssu_lost.oauth.kakao.dto.KakaoUserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class KakaoService {

    @Value("${spring.kakao.kakao_client_id}")
    private String clientId;

    @Value("${spring.kakao.kakao_redirect_uri}")
    private String redirectUri;

    public String getAccessTokenFromKakao(String code) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://kauth.kakao.com")
                .defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        KakaoTokenDto kakaoTokenResponseDto = webClient.post() // post 요청
                .uri("/oauth/token")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(KakaoTokenDto.class)
                .block();

        return kakaoTokenResponseDto.getAccessToken();
    }

    public KakaoUserInfoDto getUserInfoFromKakao(String accessToken) {

        WebClient webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com")
                .defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        return webClient.get() // get 요청
                .uri("/v2/user/me")
                .header("Authorization", "Bearer " + accessToken) // 헤더에 토큰 추가
                .retrieve()
                .bodyToMono(KakaoUserInfoDto.class) // 응답을 DTO로 변환
                .block();
    }

}
