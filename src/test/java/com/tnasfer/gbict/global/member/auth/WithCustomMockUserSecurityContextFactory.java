package com.tnasfer.gbict.global.member.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

import static org.springframework.security.test.context.TestSecurityContextHolder.setAuthentication;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockMember> {
    @Override
    public SecurityContext createSecurityContext(WithCustomMockMember annotation) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(WithCustomMockMember.username,null,
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);


        return securityContext;
    }
}
