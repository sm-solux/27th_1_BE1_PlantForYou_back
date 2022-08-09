package com.be1.plant4you.common.exception.board;

public class WrongCommentIdException extends BoardException {

    public WrongCommentIdException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}