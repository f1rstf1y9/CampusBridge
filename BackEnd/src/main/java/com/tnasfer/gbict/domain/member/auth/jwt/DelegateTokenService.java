package com.tnasfer.gbict.domain.member.auth.jwt;

import com.tnasfer.gbict.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DelegateTokenService {
    private final JwtTokenizer tokenizer;
    public String delegateAccessToken(Member member){
        Map<String, Object> claims = getClaims(member.getId());
        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKeySting = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeyString());
        long subject = member.getId();

        return tokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKeySting);
    }

    public String delegateAccessToken(long subject) {
        Map<String, Object> claims = getClaims(subject);
        Date expiration = tokenizer.getTokenExpiration(tokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKeyString = tokenizer.secretKeyEncodeBase64(tokenizer.getSecretKeyString());

        return tokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKeyString);
    }

    private static Map<String, Object> getClaims(long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", String.valueOf(id));
        return claims;
    }
}
