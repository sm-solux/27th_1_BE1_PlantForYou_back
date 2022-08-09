package com.be1.plant4you.common.exception.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BoardException extends RuntimeException {

    private final BoardErrorCode errorCode;
}