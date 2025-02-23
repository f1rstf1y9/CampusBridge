package com.tnasfer.gbict.domain.member.auth.handler.auth;

import com.google.gson.Gson;
import com.tnasfer.gbict.global.responseDto.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;



//TODO: 추가 적인 exceptionCode 에 대한 메서드가 필요함(HttpStatus 만 메서드 처리 중임 해결필요)
public class AuthenticationErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(status, message);
        setResponse(response, status, errorResponse);
    }

    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(status);
        setResponse(response, status, errorResponse);
    }

    private static void setResponse(HttpServletResponse response, HttpStatus status, ErrorResponse errorResponse) throws IOException {
        Gson gson = new Gson();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}

