package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.request.TokenRequest;
import com.be1.plant4you.auth.dto.response.TokenResponse;
import com.be1.plant4you.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    //access token 만료되었을 때 호출
    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> reissue(@RequestBody @Validated TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.reissue(tokenRequest.getAccessToken(), tokenRequest.getRefreshToken());
        HttpStatus status = (tokenResponse == null) ? BAD_REQUEST : OK;
        return ResponseEntity
                .status(status)
                .body(tokenResponse);
    }
}
