package com.tnasfer.gbict.domain.member.auth.handler.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tnasfer.gbict.domain.member.auth.filter.JwtAuthenticationFilter;
import com.tnasfer.gbict.domain.member.auth.filter.JwtVerificationFilter;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        setResponse(request.getAttribute(JwtVerificationFilter.EX_HEADER),response);
    }
    private void setResponse(Object exceptionError, HttpServletResponse response) throws IOException {
        if (exceptionError instanceof BusinessLogicException businessLogicException){
            setLog(businessLogicException.getExceptionCode().getStatus(),businessLogicException.getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED,(businessLogicException).getMessage());
        }else if (exceptionError instanceof ExceptionCode exceptionCode){
            setLog(exceptionCode.getStatus(),exceptionCode.getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED, (exceptionCode).getMessage());
        }
        else if (exceptionError instanceof RuntimeException){
            setLog(HttpStatus.UNAUTHORIZED.value(),"Not businessLogicException and exceptionCode Error");
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        }
    }

    private void setLog(int code, String msg){
        log.info("인증 오류 (catch in MemberAuthenticationEntryPoint) code: {}",code);
        log.info("인증 오류 (catch in MemberAuthenticationEntryPoint) msg : {}",msg);
    }
}
