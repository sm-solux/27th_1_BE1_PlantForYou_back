package com.be1.plant4you.exception;

public class WrongCommentIdException extends RuntimeException {

    public WrongCommentIdException(String message) {
        super(message);
    }

    public WrongCommentIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
