package com.be1.plant4you.auth.service;

import com.be1.plant4you.auth.dto.response.TokenResponse;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    //private final AuthTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private static final String USER_ID_KEY = "userId";

    //토큰 재발급
    @Transactional
    public TokenResponse reissue(String accessTokenStr, String refreshTokenStr) {
        return null;
    }
}
