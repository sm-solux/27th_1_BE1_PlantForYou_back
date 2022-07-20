package com.be1.plant4you.exception.board;

public class NotMyPostException extends BoardException {

    public NotMyPostException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}