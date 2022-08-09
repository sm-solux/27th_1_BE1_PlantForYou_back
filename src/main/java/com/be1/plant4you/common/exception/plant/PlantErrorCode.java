package com.be1.plant4you.common.exception.plant;

import com.be1.plant4you.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
public enum PlantErrorCode implements ErrorCode {

    WRONG_PLANT_DICT_ID(NOT_FOUND, "존재하지 않는 식물사전입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
