package com.example.ssu_lost.entity;

import com.example.ssu_lost.enums.OAuthProvider;
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
    @Column(name = "member_id", columnDefinition = "Binary(16)")
    private UUID id;

    @Column(nullable = false, length = 50)
    private String memberName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthProvider oAuthProvider;

    @Column(nullable = false)
    private String oAuthId;
}
