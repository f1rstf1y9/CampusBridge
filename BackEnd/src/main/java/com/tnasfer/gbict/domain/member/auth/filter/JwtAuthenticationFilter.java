package com.tnasfer.gbict.domain.member.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnasfer.gbict.domain.member.auth.jwt.DelegateTokenService;
import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


// 토큰 생성을위한 커스텀 시큐리티 필터
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final DelegateTokenService delegateTokenService;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MemberDto.Login loginMember= objectMapper.readValue(request.getInputStream(), MemberDto.Login.class);
            log.info("memberId : {}  ",loginMember.getMemberId());
            log.info("password : {}  ",loginMember.getPassword());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginMember.getMemberId(), loginMember.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        }catch (IOException e){
            throw new RuntimeException("not read to info of Member");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Member member = (Member) authResult.getPrincipal();
        String accessToken = delegateTokenService.delegateAccessToken(member);
        response.setHeader("Authorization", "Bearer " + accessToken);

        this.getSuccessHandler().onAuthenticationSuccess(request,response,authResult);
    }
}
