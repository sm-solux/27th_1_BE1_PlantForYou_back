package com.be1.plant4you.common.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_USER(NOT_FOUND, "존재하지 않는 이용자입니다."),
    NOT_FOUND_POST(NOT_FOUND, "존재하지 않는 게시글입니다."),
    NOT_FOUND_COMMENT(NOT_FOUND, "존재하지 않는 댓글입니다."),
    FORBIDDEN_POST_UPDATE(FORBIDDEN, "현재 로그인한 이용자가 작성한 게시글이 아닙니다. 자신이 작성한 게시글만 수정 가능합니다."),
    FORBIDDEN_POST_DELETE(FORBIDDEN, "현재 로그인한 이용자가 작성한 게시글이 아닙니다. 자신이 작성한 게시글만 삭제 가능합니다."),
    FORBIDDEN_COMMENT_UPDATE(FORBIDDEN, "현재 로그인한 이용자가 작성한 댓글이 아닙니다. 자신이 작성한 댓글만 수정 가능합니다."),
    FORBIDDEN_COMMENT_DELETE(FORBIDDEN, "현재 로그인한 이용자가 작성한 댓글이 아닙니다. 자신이 작성한 댓글만 삭제 가능합니다."),
    ALREADY_POST_LIKES(BAD_REQUEST, "이미 해당 게시글을 좋아요 하였습니다."),
    ALREADY_POST_SCRAP(BAD_REQUEST, "이미 해당 게시글을 스크랩 하였습니다."),
    INVALID_POST_LIKES(BAD_REQUEST, "좋아요 하지 않은 게시글이므로 좋아요 취소가 불가합니다."),
    INVALID_POST_SCRAP(BAD_REQUEST, "스크랩 하지 않은 게시글이므로 스크랩 취소가 불가합니다."),

    NOT_FOUND_PLANT(NOT_FOUND, "존재하지 않는 식물입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}