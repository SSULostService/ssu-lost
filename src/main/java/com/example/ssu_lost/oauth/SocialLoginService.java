package com.example.ssu_lost.oauth;

import com.example.ssu_lost.enums.OAuthProvider;

public interface SocialLoginService {

    OAuthProvider getProviderName();

    OAuthUserInfo getUserInfo(String code); // 사용자 정보를 공통 DTO로 반환
}
