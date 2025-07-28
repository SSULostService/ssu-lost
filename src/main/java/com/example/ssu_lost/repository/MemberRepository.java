package com.example.ssu_lost.repository;

import com.example.ssu_lost.entity.Member;
import com.example.ssu_lost.enums.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByoAuthProviderAndOAuthId(OAuthProvider oAuthProvider, String oAuthId);

}
