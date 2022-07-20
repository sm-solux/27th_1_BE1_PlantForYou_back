package com.be1.plant4you.exception.board;

import com.be1.plant4you.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    WRONG_POST_ID(NOT_FOUND, "존재하지 않는 게시글입니다."),
    WRONG_COMMENT_ID(NOT_FOUND, "존재하지 않는 댓글입니다."),
    WRONG_COMMENT2_ID(BAD_REQUEST, "댓글에만 대댓글 작성이 가능합니다."),
    NOT_MY_POST_UPDATE(UNAUTHORIZED, "현재 로그인한 이용자가 작성한 게시글이 아닙니다. 자신이 작성한 게시글만 수정 가능합니다."),
    NOT_MY_POST_DELETE(UNAUTHORIZED, "현재 로그인한 이용자가 작성한 게시글이 아닙니다. 자신이 작성한 게시글만 삭제 가능합니다."),
    NOT_MY_COMMENT_UPDATE(UNAUTHORIZED, "현재 로그인한 이용자가 작성한 댓글이 아닙니다. 자신이 작성한 댓글만 수정 가능합니다."),
    NOT_MY_COMMENT_DELETE(UNAUTHORIZED, "현재 로그인한 이용자가 작성한 댓글이 아닙니다. 자신이 작성한 댓글만 삭제 가능합니다."),
    DUPLICATE_REQUEST_LIKES(BAD_REQUEST, "이미 해당 게시글을 좋아요 하였습니다."),
    DUPLICATE_REQUEST_SCRAP(BAD_REQUEST, "이미 해당 게시글을 스크랩 하였습니다."),
    NO_PREV_REQUEST_LIKES(BAD_REQUEST, "좋아요 하지 않은 게시글이므로 좋아요 취소가 불가합니다."),
    NO_PREV_REQUEST_SCRAP(BAD_REQUEST, "스크랩 하지 않은 게시글이므로 스크랩 취소가 불가합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}