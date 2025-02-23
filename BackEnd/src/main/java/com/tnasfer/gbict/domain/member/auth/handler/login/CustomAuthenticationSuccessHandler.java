package com.tnasfer.gbict.domain.member.auth.handler.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tnasfer.gbict.domain.member.dto.MemberDto;
import com.tnasfer.gbict.domain.member.entity.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Member member = (Member) authentication.getPrincipal();

        MemberDto.Response.Login login = MemberDto.Response.Login.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .name(member.getName())
                .build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        setResponse(response, gson, login);
        printLoginInfo(member);
    }

    private static void setResponse(HttpServletResponse response, Gson gson, MemberDto.Response.Login login) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(gson.toJson(login, MemberDto.Response.Login.class));
    }

    private void printLoginInfo(Member member) {
        log.info("# Authenticated Successfully!");
        log.info("# 로그인 이름: {}", member.getName());
        log.info("# 로그인 멤버 ID {}", member.getMemberId());
    }

}
