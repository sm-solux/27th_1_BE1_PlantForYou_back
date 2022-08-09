package com.be1.plant4you.auth.token;

import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.common.auth.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class AuthTokenProvider {

    @Value("${app.auth.accessTokenExpiry}")
    private Long accessTokenExpiry;

    @Value("${app.auth.refreshTokenExpiry}")
    private Long refreshTokenExpiry;

    private final Key key;
    private final UserRepository userRepository;

    private static final String USER_ID_KEY = "userId";

    @Autowired
    public AuthTokenProvider(@Value("${app.auth.tokenSecret}") String secretKey, UserRepository userRepository) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.userRepository = userRepository;
    }

    //백엔드 API에 대한 JWT token 생성
    public AuthToken createAccessToken(String providerId, Long userId) {
        Date expiryDate = getExpiryDate(accessTokenExpiry);
        return new AuthToken(providerId, userId, expiryDate, key);
    }

    public AuthToken createRefreshToken() {
        Date expiryDate = getExpiryDate(refreshTokenExpiry);
        return new AuthToken(expiryDate, key);
    }

    private static Date getExpiryDate(Long expiry) {
        return new Date(System.currentTimeMillis() + expiry);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(key, token);
    }

    public Authentication getAuthentication(AuthToken authToken) {
        if (authToken.validate()) {
            Claims claims = authToken.getTokenClaims();
            Long userId = claims.get(USER_ID_KEY, Long.class);
            Optional<User> userOptional = userRepository.findById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(""));
                UserPrincipal principal = UserPrincipal.create(user);
                return new UsernamePasswordAuthenticationToken(principal, authToken, authorities);
            }
        }
        throw new RuntimeException();
    }
}
