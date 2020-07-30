package com.magicwater.springbook.persistance;

import java.util.Optional;

import com.magicwater.springbook.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmail(String email);
    
}