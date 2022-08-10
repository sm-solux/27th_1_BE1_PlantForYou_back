package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.request.TokenRequest;
import com.be1.plant4you.auth.dto.response.TokenResponse;
import com.be1.plant4you.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    //access token 만료되었을 때 호출 (access token, refresh token 필수! -> 없으면 201 외 에러코드 전달)
    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> reissue(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.reissue(tokenRequest.getAccessToken(), tokenRequest.getRefreshToken());
        int status = (tokenResponse == null) ? 200 : 201;
        return ResponseEntity.status(status)
                             .body(tokenResponse);
    }
}
