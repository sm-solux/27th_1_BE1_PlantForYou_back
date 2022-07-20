package com.be1.plant4you.exception;

public class WrongPostIdException extends BoardException {

    public WrongPostIdException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}