package com.be1.plant4you.exception;

public class DuplicateRequestException extends InvalidRequestException {

    public DuplicateRequestException(String message) {
        super(message);
    }

    public DuplicateRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
