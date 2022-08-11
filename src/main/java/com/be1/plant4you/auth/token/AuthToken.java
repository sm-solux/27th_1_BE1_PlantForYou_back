package com.be1.plant4you.auth.token;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@Getter
@RequiredArgsConstructor
public class AuthToken {

    private final Key key; //토큰 생성시 사용할 비밀키
    private final String token;

    private static final String USER_ID_KEY = "userId";

    public AuthToken(String providerId, Long userId, Date expiry, Key key) {
        this.key = key;
        this.token = createToken(providerId, userId, expiry);
    }

    public AuthToken(Date expiry, Key key) {
        this.key = key;
        this.token = createToken(null, null, expiry);
    }

    private String createToken(String providerId, Long userId, Date expiry) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(providerId)
                .signWith(key, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .setExpiration(expiry);
        return (userId != null ? jwtBuilder.claim(USER_ID_KEY, userId) : jwtBuilder).compact();
    }

    public boolean validate() {
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            log.error("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid");
        }
        return null;
    }

    public Claims getExpiredTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token.");
            return e.getClaims();
        } catch (SecurityException e) {
            log.error("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid");
        }
        return null;
    }
}
