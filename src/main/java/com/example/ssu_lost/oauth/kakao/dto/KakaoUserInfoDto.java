package com.example.ssu_lost.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoDto {

    private Long id;
    @JsonProperty("connected_at")
    private String connectedAt;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoAccount {

        @JsonProperty("profile")
        private Profile profile;

        @Getter
        @NoArgsConstructor
        @ToString
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Profile {

            @JsonProperty("nickname")
            private String nickname;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;
            @JsonProperty("is_default_image")
            private boolean isDefaultImage;
        }
    }
}
