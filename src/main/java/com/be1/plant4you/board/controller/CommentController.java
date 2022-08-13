package com.be1.plant4you.board.controller;

import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.board.dto.response.CommentResponse;
import com.be1.plant4you.board.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.be1.plant4you.common.validation.ValidationGroup.*;
import static org.springframework.http.HttpStatus.*;

@Api(tags = "게시판 댓글 API")
@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글|대댓글 작성")
    @PostMapping
    public ResponseEntity<List<CommentResponse>> saveComment(@RequestBody @Validated(CommentSave.class) CommentRequest commentRequest) {
        return ResponseEntity.status(CREATED).body(commentService.saveComment(commentRequest));
    }

    @Operation(summary = "댓글|대댓글 수정")
    @PutMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> updateComment(@PathVariable Long commentId,
                                                               @RequestBody @Validated(CommentUpdate.class) CommentRequest commentRequest) {
        return ResponseEntity.status(OK).body(commentService.updateComment(commentId, commentRequest));
    }

    @Operation(summary = "댓글|대댓글 삭제")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.status(OK).body(commentService.deleteComment(commentId));
    }
}