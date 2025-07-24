package com.example.ssu_lost.service;

import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.oauth.google.service.GoogleService;
import com.example.ssu_lost.oauth.kakao.service.KakaoService;
import com.example.ssu_lost.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final GoogleService googleService;

    public String socialLogin(OAuthProvider oAuthProvider) {
        return "";
    }
}
