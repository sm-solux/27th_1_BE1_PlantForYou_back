package com.be1.plant4you.auth.dto.request;

import lombok.Getter;

@Getter
public class TokenRequest {

    private String accessToken;
    private String refreshToken;
}
