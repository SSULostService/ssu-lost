package com.example.ssu_lost.entity;

import com.example.ssu_lost.enums.OAuthProvider;
import com.example.ssu_lost.oauth.OAuthUserInfo;
import jakarta.persistence.*;
        import lombok.*;
        import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthProvider oAuthProvider;

    @Column(nullable = false)
    private String oAuthId;

    public static Member from(OAuthUserInfo userInfo) {
        return Member.builder()
                .id(UUID.randomUUID())
                .memberName(userInfo.getName())
                .oAuthProvider(userInfo.getProvider())
                .oAuthId(userInfo.getProviderId())
                .build();
    }
}
