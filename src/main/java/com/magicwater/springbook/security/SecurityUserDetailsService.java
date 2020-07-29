package com.magicwater.springbook.security;

import java.util.Optional;

import com.magicwater.springbook.domain.Member;
import com.magicwater.springbook.persistance.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    
    @Autowired
    private MemberRepository memberRepo;

    // 조회한 회원 정보를 SecurityUser 객체로 감싸서 return
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional = memberRepo.findById(username);
        if(!optional.isPresent()){
            throw new UsernameNotFoundException(username +"사용자 없음");
        } 
        else {
            Member member = optional.get();
            return new SecurityUser(member);
        }
    }
}