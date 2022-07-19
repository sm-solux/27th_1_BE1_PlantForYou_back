package com.be1.plant4you.exception;

public class NotMyCommentException extends InvalidRequestException {

    public NotMyCommentException(String message) {
        super(message);
    }

    public NotMyCommentException(String message, Throwable cause) {
        super(message, cause);
    }
}
