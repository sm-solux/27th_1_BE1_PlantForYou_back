package com.be1.plant4you.board.controller;

import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.board.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.be1.plant4you.common.validation.ValidationGroup.*;

@Api(tags = "게시판 댓글 API")
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글|대댓글 작성")
    @PostMapping
    public String saveComment(@RequestBody @Validated(CommentSave.class) CommentRequest commentRequest) {
        commentService.saveComment(commentRequest);
        return "댓글 작성이 완료되었습니다.";
    }

    @Operation(summary = "댓글|대댓글 수정")
    @PutMapping("/{commentId}")
    public String updateComment(@PathVariable Long commentId,
                                @RequestBody @Validated(CommentUpdate.class) CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest);
        return "댓글 수정이 완료되었습니다.";
    }

    @Operation(summary = "댓글|대댓글 삭제")
    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "댓글 삭제가 완료되었습니다.";
    }
}