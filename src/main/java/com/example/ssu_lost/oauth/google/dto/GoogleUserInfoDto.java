package com.example.ssu_lost.oauth.google.dto;

import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.oauth.OAuthUserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUserInfoDto implements OAuthUserInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("email")
    private String email;

    @Override
    public String getProviderId() {
        return this.id;
    }

    @Override
    public OAuthProvider getProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public String getProfileImageUrl() {
        return this.picture;
    }
}
