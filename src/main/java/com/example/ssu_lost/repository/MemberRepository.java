package com.example.ssu_lost.repository;

import com.example.ssu_lost.entity.Member;
import com.example.ssu_lost.enums.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query("SELECT m FROM Member m WHERE m.oAuthProvider = :oAuthProvider AND m.oAuthId = :oAuthId")
    Optional<Member> findBySocialLoginInfo(@Param("oAuthProvider") OAuthProvider oAuthProvider, @Param("oAuthId") String oAuthId);

}
