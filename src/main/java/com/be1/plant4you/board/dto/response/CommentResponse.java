package com.be1.plant4you.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Schema(description = "게시글 상세내용 조회 시 반환하는 댓글 DTO")
@Getter
public class CommentResponse {

    @Schema(description = "댓글 ID", example = "1")
    private Long commentId;

    @Schema(description = "댓글 작성자 이름", example = "다은")
    private String writerName;

    @Schema(description = "댓글 내용", example = "좋은 정보 감사합니다!")
    private String content;

    @Schema(description = "댓글 작성일", example = "07/21 20:30")
    @JsonFormat(pattern = "MM/dd HH:mm", shape = STRING)
    private LocalDateTime createdDate;

    @Schema(description = "대댓글에 대한 삭제 여부", example = "false")
    private Boolean isDelete; //대댓글에 적용

    @Schema(description = "댓글에 대한 대댓글 리스트, 대댓글에 대해서는 의미X")
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