package com.example.ssu_lost.oauth.login.service;

import com.example.ssu_lost.entity.Member;
import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.global.code.ErrorCode;
import com.example.ssu_lost.global.exception.LoginException;
import com.example.ssu_lost.oauth.OAuthUserInfo;
import com.example.ssu_lost.oauth.google.service.GoogleService;
import com.example.ssu_lost.oauth.kakao.service.KakaoService;
import com.example.ssu_lost.repository.MemberRepository;
import com.example.ssu_lost.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class LoginService {

    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final GoogleService googleService;
    private final JwtTokenProvider jwtTokenProvider;

    public String socialLogin(OAuthProvider oAuthProvider, String code) {
        OAuthUserInfo userInfo = switch (oAuthProvider) {
            case GOOGLE -> googleService.getUserInfo(code);
            case KAKAO -> kakaoService.getUserInfo(code);
            default -> throw new LoginException(ErrorCode._LOGIN_BAD_REQUEST);
        };

        Member member = memberRepository.findBySocialLoginInfo(oAuthProvider, userInfo.getProviderId())
                .orElseGet(() -> memberRepository.save(Member.from(userInfo)));

        return jwtTokenProvider.createAccessToken(member.getId().toString());
    }
}
