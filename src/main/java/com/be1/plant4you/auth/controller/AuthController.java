package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.request.TokenRequest;
import com.be1.plant4you.auth.dto.response.TokenResponse;
import com.be1.plant4you.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Api(tags = "인증 API")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "엑세스 토큰 만료 시 토큰 재발급 수행")
    @PostMapping("/auth/refresh")
    public ResponseEntity<TokenResponse> reissue(@RequestBody @Validated TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.reissue(tokenRequest.getAccessToken(), tokenRequest.getRefreshToken());
        HttpStatus status = (tokenResponse == null) ? BAD_REQUEST : OK;
        return ResponseEntity
                .status(status)
                .body(tokenResponse);
    }
}
