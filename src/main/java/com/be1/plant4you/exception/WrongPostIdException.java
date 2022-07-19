package com.be1.plant4you.exception;

public class WrongPostIdException extends RuntimeException {

    public WrongPostIdException(String message) {
        super(message);
    }

    public WrongPostIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
