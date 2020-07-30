package com.magicwater.springbook.security;

import com.magicwater.springbook.config.JwtAuthenticationFilter;
import com.magicwater.springbook.config.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private SecurityUserDetailsService userDetailsService;

    // 암호화에 필요한 PasswordEncoder 를 Bean 등록 
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // authenticationManager 를 Bean 등록 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security.userDetailsService(userDetailsService);

        security.authorizeRequests().antMatchers("/","/system/**", "/static/**").permitAll();
        security.authorizeRequests().antMatchers("/board/**").authenticated();
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        security.csrf().disable();
        security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
        security.exceptionHandling().accessDeniedPage("/system/accessDenied");
        security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");

        // JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 전에 넣는다. 
        security.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }

    // / system 으로 시작하는 경로 -> 인증되지 않은 모든 사용자 접근 가능 
    // /board 로 시작하는 경로 -> 인증된 사용자만 접근 가능 
    // /admin -> admin 권한 가진 사용자만 접근 가능 

    // 인증되지 않은 사용자가 /board 로 시작하는 경로 요청 -> /system/login 으로 리다이렉트 
    // 로그인 성공 -> /board/getboardList 리다이렉트 
    // 사용자가 /system/logout 요청 -> 세션 강제 종료, 인덱스 페이지로 이동 


}