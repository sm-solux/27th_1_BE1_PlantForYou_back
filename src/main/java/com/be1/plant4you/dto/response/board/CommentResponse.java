package com.be1.plant4you.dto.response.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Getter
public class CommentResponse {

    private Long commentId;

    private String writerName;

    private String content;

    @JsonFormat(pattern = "MM/dd HH:mm", shape = STRING)
    private LocalDateTime createdDate;

    private Boolean isDelete; //대댓글에 적용

    private List<CommentResponse> childList = new ArrayList<>();

    public CommentResponse(Long commentId, String writerName, String content,
                           LocalDateTime createdDate, Boolean isDelete) {
        this.commentId = commentId;
        this.writerName = writerName;
        this.content = content;
        this.createdDate = createdDate;
        this.isDelete = isDelete;
    }

    public void changeChildList(List<CommentResponse> childList) {
        this.childList = childList;
    }
}