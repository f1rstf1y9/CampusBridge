package com.tnasfer.gbict.domain.member.auth.filter;

import com.tnasfer.gbict.domain.member.auth.jwt.DelegateTokenService;
import com.tnasfer.gbict.domain.member.auth.jwt.JwtTokenizer;
import com.tnasfer.gbict.domain.member.entity.Role;
import com.tnasfer.gbict.domain.member.service.MemberRoleService;
import com.tnasfer.gbict.global.error.code.ExceptionCode;
import com.tnasfer.gbict.global.error.exception.BusinessLogicException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtVerificationFilter extends OncePerRequestFilter {
    public final static String EX_HEADER = "exception";
    private final JwtTokenizer jwtTokenizer;
    private final MemberRoleService service;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String checkHeader = request.getHeader("Authorization");
        return checkHeader == null || !checkHeader.startsWith("Bearer");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String encodeKey = jwtTokenizer.secretKeyEncodeBase64(jwtTokenizer.getSecretKeyString());
        String jwtToken =  request.getHeader("Authorization").replace("Bearer ", "");;
        try{
            Object id = getIdInJwt(encodeKey,jwtToken);
            List<Role> roleList = service.findRoleListByMemberId(Long.parseLong(String.valueOf(id)));
            List<GrantedAuthority> grantedAuthorities = getGrantedAuthorityList(roleList);
            Authentication authentication = new UsernamePasswordAuthenticationToken(id,null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (ExpiredJwtException ne){
            request.setAttribute(EX_HEADER, ExceptionCode.EXPIRED_TOKEN);
        }catch (BusinessLogicException be){
            request.setAttribute(EX_HEADER,be);
        }
        catch (ClassCastException ce){
            request.setAttribute(EX_HEADER, ExceptionCode.ACCESS_DENIED);
        }catch (Exception e){
            request.setAttribute(EX_HEADER, ExceptionCode.UNAUTHORIZED);
        }

        filterChain.doFilter(request, response);
    }

    private List<GrantedAuthority> getGrantedAuthorityList(List<Role> roleList) {
        List<String> stringList = new ArrayList<>();
        for (Role role : roleList){
            stringList.add(role.getRoleName().getRoleName());
        }
       return AuthorityUtils.createAuthorityList(stringList);
    }


    private Object getIdInJwt(String encodeKey, String jwtToken){
        Map<String, Object> claims = jwtTokenizer.getClaims(jwtToken,encodeKey).getPayload();
        return  claims.get("id");
    }
}
