package com.be1.plant4you.exception;

public class NoPrevRequestException extends InvalidRequestException {

    public NoPrevRequestException(String message) {
        super(message);
    }

    public NoPrevRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
