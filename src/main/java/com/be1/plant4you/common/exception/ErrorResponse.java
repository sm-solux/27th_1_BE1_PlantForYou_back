package com.be1.plant4you.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "예외 발생 시 반환하는 예외 정보 DTO")
@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "에러 발생시간", example = "2022-08-07 18:29:25")
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul", shape = STRING)
    private final LocalDateTime timestamp = LocalDateTime.now();

    @Schema(description = "기존 에러 상태코드", example = "403")
    private final int status;

    @Schema(description = "기존 에러 코드", example = "FORBIDDEN")
    private final String error;

    @Schema(description = "새로 정의한 에러 코드", example = "INVALID_ACCESS_TOKEN")
    private final String code;

    @Schema(description = "새로 정의한 에러 메시지", example = "유효하지 않은 엑세스 토큰입니다.")
    private final String message;

    @Schema(description = "요청한 path", example = "/posts/5")
    private String path;

    public static ResponseEntity<ErrorResponse> toResponseEntity(CustomException e, String path) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.create(errorCode, path));
    }

    public static ErrorResponse create(ErrorCode errorCode, String path) {
        return ErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .error(errorCode.getHttpStatus().name())
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .path(path)
                .build();
    }

    public String convertToJson() {
        try {
            //ObjectMapper가 'jackson-datatype-jsr310' 모듈 찾아주지 못해서 발생하는 LocalDateTime JsonFormat 문제 해결
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule()) //관련 모듈 직접 add
                    .build();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}