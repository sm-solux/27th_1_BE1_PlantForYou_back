package com.be1.plant4you.exception.board;

public class WrongPostIdException extends BoardException {

    public WrongPostIdException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}