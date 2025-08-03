package com.example.ssu_lost.service;

import com.example.ssu_lost.entity.Member;
import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.oauth.OAuthUserInfo;
import com.example.ssu_lost.oauth.SocialLoginService;
import com.example.ssu_lost.repository.MemberRepository;
import com.example.ssu_lost.security.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoginService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final Map<OAuthProvider, SocialLoginService> socialLoginServices;

    public LoginService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider, List<SocialLoginService> services) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.socialLoginServices = services.stream()
                .collect(Collectors.toMap(SocialLoginService::getProviderName, Function.identity()));
    }

    public String socialLogin(OAuthProvider oAuthProvider, String code) {
        SocialLoginService socialLoginService = socialLoginServices.get(oAuthProvider);
        OAuthUserInfo userInfo = socialLoginService.getUserInfo(code);

        Member member = memberRepository.findBySocialLoginInfo(oAuthProvider, userInfo.getProviderId())
                .orElseGet(() -> memberRepository.save(Member.from(userInfo)));

        return jwtTokenProvider.createAccessToken(member.getId().toString());
    }
}
