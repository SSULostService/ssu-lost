package com.example.ssu_lost.oauth.google.service;

import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.oauth.SocialLoginService;
import com.example.ssu_lost.oauth.google.dto.GoogleTokenDto;
import com.example.ssu_lost.oauth.google.dto.GoogleUserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class GoogleService implements SocialLoginService {

    @Value("${spring.google.client_id}")
    private String clientId;

    @Value("${spring.google.redirect_uri}")
    private String redirectUri;

    @Value("${spring.google.client_secret}")
    private String clientSecret;

    public String getAccessTokenFromGoogle(String code) {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://oauth2.googleapis.com")
                .defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("client_secret", clientSecret);
        params.add("grant_type", "authorization_code");

        GoogleTokenDto googleTokenDto = webClient.post()
                .uri("/token")
                .bodyValue(params)
                .retrieve()
                .bodyToMono(GoogleTokenDto.class)
                .block();

        return googleTokenDto.getAccessToken();
    }

    @Override
    public GoogleUserInfoDto getUserInfo(String code) {

        String accessToken = getAccessTokenFromGoogle(code);

        WebClient webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com")
                .build();

        return webClient.get()
                .uri("/oauth2/v2/userinfo")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(GoogleUserInfoDto.class)
                .block();
    }

    @Override
    public OAuthProvider getProviderName() {
        return OAuthProvider.GOOGLE;
    }
}
