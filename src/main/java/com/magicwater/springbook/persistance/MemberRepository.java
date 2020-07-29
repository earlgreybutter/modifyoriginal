package com.magicwater.springbook.persistance;

import com.magicwater.springbook.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}