package com.tnasfer.gbict.domain.member.auth.handler.login;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tnasfer.gbict.global.responseDto.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String InvalidCredentialsMessage = "Invalid credentials";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logInfoErrorReason(exception);
        String exceptionMessage = exception.getClass().getName().equals(BadCredentialsException.class.getName()) ?
                InvalidCredentialsMessage : exception.getMessage();
        ErrorResponse errorResponse = setErrorResponse(exceptionMessage);
        sendErrorResponse(errorResponse, response);

    }

    private void sendErrorResponse(ErrorResponse errorResponse, HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.getWriter().write(gson.toJson(errorResponse,ErrorResponse.class));
        response.setStatus(errorResponse.getStatus());
    }

    private ErrorResponse setErrorResponse(String exceptionMessage) {
        if (exceptionMessage == null || exceptionMessage.equals(HttpStatus.UNAUTHORIZED.getReasonPhrase())){
            return  ErrorResponse.of(HttpStatus.UNAUTHORIZED);
        }
        return ErrorResponse.of(HttpStatus.UNAUTHORIZED,exceptionMessage);
    }

    private void logInfoErrorReason(AuthenticationException exception) {
        log.error("# Authentication Failed..: {}", exception.getMessage());
        log.error("# Authentication Failed Exception Classname: {}", exception.getClass().getName());
    }
}
