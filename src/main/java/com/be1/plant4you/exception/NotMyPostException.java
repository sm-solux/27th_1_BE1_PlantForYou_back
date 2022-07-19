package com.be1.plant4you.exception;

public class NotMyPostException extends InvalidRequestException {

    public NotMyPostException(String message) {
        super(message);
    }

    public NotMyPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
