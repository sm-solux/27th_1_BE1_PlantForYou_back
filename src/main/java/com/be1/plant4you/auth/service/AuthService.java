package com.be1.plant4you.auth.service;

import com.be1.plant4you.auth.domain.RefreshToken;
import com.be1.plant4you.auth.dto.response.TokenResponse;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import com.be1.plant4you.auth.token.AuthToken;
import com.be1.plant4you.auth.token.AuthTokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String USER_ID_KEY = "userId";

    //토큰 재발급
    @Transactional
    public TokenResponse reissue(String accessTokenStr, String refreshTokenStr) {
        AuthToken accessToken = tokenProvider.convertAuthToken(accessTokenStr);
        Claims claims = accessToken.getExpiredTokenClaims();
        if (claims == null) return null;

        String providerId = claims.getSubject();
        Long userId = claims.get(USER_ID_KEY, Long.class);

        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByKey(userId);
        if (refreshTokenOptional.isPresent()) {
            RefreshToken dbRefreshToken = refreshTokenOptional.get();
            AuthToken refreshToken = tokenProvider.convertAuthToken(dbRefreshToken.getValue());

            if (refreshToken.validate() && refreshToken.getToken().equals(refreshTokenStr)) {
                //refresh token 존재하면서 유효하면서 요청값과 일치
                AuthToken newAccessToken = tokenProvider.createAccessToken(providerId, userId);
                AuthToken newRefreshToken = tokenProvider.createRefreshToken();
                dbRefreshToken.updateValue(newRefreshToken.getToken());

                //JWT token 재발급하고 access, refresh token 전달 -> 200 상태코드 반환
                return TokenResponse.builder()
                        .accessToken(newAccessToken.getToken())
                        .refreshToken(newRefreshToken.getToken())
                        .build();
            }
        }
        //refresh token 존재하지 않거나 만료 -> 400 상태코드 반환
        return null;
    }
}
