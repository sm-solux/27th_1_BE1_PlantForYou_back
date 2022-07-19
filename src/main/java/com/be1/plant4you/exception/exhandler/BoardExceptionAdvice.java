package com.be1.plant4you.exception.exhandler;

import com.be1.plant4you.controller.CommentController;
import com.be1.plant4you.controller.PostController;
import com.be1.plant4you.dto.response.ex.ExResponse;
import com.be1.plant4you.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice(assignableTypes = {PostController.class, CommentController.class})
public class BoardExceptionAdvice {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public ExResponse wrongPostIdExceptionHandler(WrongPostIdException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public ExResponse wrongCommentIdExceptionHandler(WrongCommentIdException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler
    public ExResponse notMyPostExceptionHandler(NotMyPostException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(UNAUTHORIZED.value())
                .error(UNAUTHORIZED)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler
    public ExResponse notMyCommentExceptionHandler(NotMyCommentException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(UNAUTHORIZED.value())
                .error(UNAUTHORIZED)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public ExResponse duplicateRequestExceptionHandler(DuplicateRequestException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler
    public ExResponse noPrevRequestExceptionHandler(NoPrevRequestException e, HttpServletRequest request) {
        log.error("", e);
        return ExResponse.builder()
                .status(BAD_REQUEST.value())
                .error(BAD_REQUEST)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}