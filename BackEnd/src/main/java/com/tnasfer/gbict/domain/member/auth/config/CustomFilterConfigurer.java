package com.tnasfer.gbict.domain.member.auth.config;

import com.tnasfer.gbict.domain.member.auth.filter.JwtAuthenticationFilter;
import com.tnasfer.gbict.domain.member.auth.filter.JwtVerificationFilter;
import com.tnasfer.gbict.domain.member.auth.handler.login.CustomAuthenticationFailureHandler;
import com.tnasfer.gbict.domain.member.auth.handler.login.CustomAuthenticationSuccessHandler;
import com.tnasfer.gbict.domain.member.auth.jwt.DelegateTokenService;
import com.tnasfer.gbict.domain.member.auth.jwt.JwtTokenizer;
import com.tnasfer.gbict.domain.member.service.MemberRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
//로그인 필터에 대한 커스텀
public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
    private final JwtTokenizer jwtTokenizer;
    private final DelegateTokenService delegateTokenService;
    private final MemberRoleService memberRoleService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, delegateTokenService);
        jwtAuthenticationFilter.setFilterProcessesUrl("/public/login");
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer,memberRoleService);
        builder.addFilter(jwtAuthenticationFilter).addFilterAfter(jwtVerificationFilter,JwtAuthenticationFilter.class);

    }
}
