package com.be1.plant4you.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Schema(description = "엑세스 토큰 만료 시 토큰 재발급 요청 DTO")
@Getter
public class TokenRequest {

    @NotBlank
    @Schema(description = "엑세스 토큰", required = true)
    private String accessToken;

    @NotBlank
    @Schema(description = "리프레시 토큰", required = true)
    private String refreshToken;
}
