package com.be1.plant4you.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Schema(description = "예외 발생 시 반환하는 예외 정보 DTO")
@Getter
@Builder
@AllArgsConstructor
public class ExResponse {

    @Schema(description = "예외 #", example = "400")
    private int status;

    @Schema(description = "예외 코드", example = "BAD_REQUEST")
    private HttpStatus error;

    @Schema(description = "예외 메시지", example = "존재하지 않는 게시글입니다.")
    private String message;

    @Schema(description = "요청한 path", example = "/posts/5")
    private String path;
}