package com.tnasfer.gbict.domain.member.auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@Getter
@Component
public class JwtTokenizer {
    @Value("${jwt.secretKey}")
    private String secretKeyString;

    @Value("${jwt.expirationMinutes}")
    private int accessTokenExpirationMinutes;

    public String secretKeyEncodeBase64(String secretKeyString){
        return Encoders.BASE64.encode(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      long subject,
                                      Date expiration,
                                      String base64EncodedSecretKeyString) {
        SecretKey key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.builder()
                .claims(claims)
                .subject(String.valueOf(subject))
                .expiration(expiration)
                .issuedAt(Calendar.getInstance().getTime())
                .signWith(key)
                .compact();
    }
    public Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationMinutes);

        return calendar.getTime();
    }
    public Jws<Claims> getClaims(String jws, String base64EncodedSecretKeyString) {
        SecretKey key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jws);
    }

    public String getSubject(String jws, String base64EncodedSecretKeyString) {
        SecretKey key = getKeyBase64DecodedKey(base64EncodedSecretKeyString);

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jws)
                .getPayload()
                .getSubject();
    }


    private SecretKey getKeyBase64DecodedKey(String base64EncodedSecretKeyString) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64EncodedSecretKeyString));
    }

}
