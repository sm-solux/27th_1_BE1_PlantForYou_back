package com.be1.plant4you.exception.board;

public class NotMyCommentException extends BoardException {

    public NotMyCommentException(BoardErrorCode errorCode) {
        super(errorCode);
    }
}