package com.magicwater.springbook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.userDetailsService(userDetailsService);

        security.authorizeRequests().antMatchers("/", "/system/**").permitAll();
        security.authorizeRequests().antMatchers("/board/**").authenticated();
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        security.csrf().disable();
        security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
        security.exceptionHandling().accessDeniedPage("/system/accessDenied");
        security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");

    }

    // / system 으로 시작하는 경로 -> 인증되지 않은 모든 사용자 접근 가능 
    // /board 로 시작하는 경로 -> 인증된 사용자만 접근 가능 
    // /admin -> admin 권한 가진 사용자만 접근 가능 

    // 인증되지 않은 사용자가 /board 로 시작하는 경로 요청 -> /system/login 으로 리다이렉트 
    // 로그인 성공 -> /board/getboardList 리다이렉트 
    // 사용자가 /system/logout 요청 -> 세션 강제 종료, 인덱스 페이지로 이동 

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}