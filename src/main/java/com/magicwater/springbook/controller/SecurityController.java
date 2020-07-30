package com.magicwater.springbook.controller;

import com.magicwater.springbook.config.JwtTokenProvider;
import com.magicwater.springbook.domain.Member;
import com.magicwater.springbook.persistance.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final PasswordEncoder PasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;


    // [회원가입] 링크 클릭 시, 화면 전환 
    @GetMapping("/system/join")
    public ModelAndView joinView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/system/join");
        return modelAndView;
    }

    // 회원가입 
    @PostMapping("/system/join")
    public Long join(Member member){

        return null;
    }
    
    @GetMapping("/system/login")
    public void login(){}

    @GetMapping("/system/accessDenied")
    public void accessDenied(){}

    @GetMapping("/system/logout")
    public void logout(){}

    @GetMapping("/admin/adminPage")
    public void admin(){}
}