package com.be1.plant4you.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(CustomException e, HttpServletRequest request) {
        log.error(e.getErrorCode().getMessage());
        return ErrorResponse.toResponseEntity(e, request.getRequestURI());
    }
}