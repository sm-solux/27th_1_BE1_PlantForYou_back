package com.be1.plant4you.exception;

public class NotMyCommentException extends BoardException {

    public NotMyCommentException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}