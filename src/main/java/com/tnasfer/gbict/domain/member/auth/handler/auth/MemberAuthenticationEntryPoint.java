package com.tnasfer.gbict.domain.member.auth.handler.auth;

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

        // 토큰이 없을 때
        if (exceptionError==null){
            setLog(HttpStatus.UNAUTHORIZED.value(),ExceptionCode.INVALID_TOKEN.getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response,HttpStatus.UNAUTHORIZED,ExceptionCode.NO_AUTH_HEADER.getMessage());
        }

        //맴버 검증 오류
        else if (exceptionError instanceof BusinessLogicException businessLogicException){
            setLog(businessLogicException.getExceptionCode().getStatus(),businessLogicException.getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED,(businessLogicException).getMessage());
        }

        //토큰 만료 혹은 토큰 검증 오류
        else if (exceptionError instanceof ExceptionCode exceptionCode){
            setLog(exceptionCode.getStatus(),exceptionCode.getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED, (exceptionCode).getMessage());
        }

        //그 이외의 오류 확인 필요함
        else if (exceptionError instanceof RuntimeException){
            setLog(HttpStatus.UNAUTHORIZED.value(), ((RuntimeException) exceptionError).getMessage());
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        }else if (exceptionError instanceof Exception exception){
            setLog(HttpStatus.UNAUTHORIZED.value(), (exception.getMessage()));
            AuthenticationErrorResponder.sendErrorResponse(response, HttpStatus.BAD_REQUEST);
        }

    }

    private void setLog(int code, String msg){
        log.info("인증 오류 (catch in MemberAuthenticationEntryPoint) code: {}",code);
        log.info("인증 오류 (catch in MemberAuthenticationEntryPoint) msg : {}",msg);
    }
}
