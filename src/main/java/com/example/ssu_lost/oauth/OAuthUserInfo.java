package com.example.ssu_lost.oauth;

import com.example.ssu_lost.enums.OAuthProvider;

public interface OAuthUserInfo {

    String getProviderId();

    OAuthProvider getProvider();

    String getName();

    String getProfileImageUrl();
}
