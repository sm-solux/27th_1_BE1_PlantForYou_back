package com.be1.plant4you.common.exception;

import com.be1.plant4you.common.validation.ValidationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(CustomException e, HttpServletRequest request) {
        log.error(e.getErrorCode().getMessage());
        return ErrorResponse.toResponseEntity(e, request.getRequestURI());
    }

    @ExceptionHandler
    public ResponseEntity<List<ValidationResponse>> validationHandler(MethodArgumentNotValidException e) {
        List<ValidationResponse> body = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            body.add(ValidationResponse.create(fieldError));
        }

        log.error(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}