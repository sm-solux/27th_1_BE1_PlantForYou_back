package com.be1.plant4you.common.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Schema(description = "validation 검사 실패 시 반환하는 DTO")
@Getter
@Builder
@AllArgsConstructor
public class ValidationResponse {

    @Schema(description = "유효성 검사 실패 필드")
    private String field;

    @Schema(description = "유효성 검사 실패 값")
    private Object rejectedValue;

    @Schema(description = "유효성 검사 실패 기본 메시지")
    private String defaultMessage;

    public static ValidationResponse create(FieldError fieldError) {
        return ValidationResponse.builder()
                .field(fieldError.getField())
                .rejectedValue(fieldError.getRejectedValue())
                .defaultMessage(fieldError.getDefaultMessage())
                .build();
    }
}
