package com.be1.plant4you.board.controller;

import com.be1.plant4you.common.auth.CurrentUser;
import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.board.service.CommentService;
import com.be1.plant4you.common.auth.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시판 댓글 API")
@RequiredArgsConstructor
@RequestMapping("/api/posts/cmts")
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/{postId}")
    public String saveCmt(@CurrentUser UserPrincipal userPrincipal,
                          @PathVariable Long postId,
                          @RequestBody @Validated CommentRequest commentRequest) {
        commentService.saveCmt(userPrincipal.getUserId(), postId, commentRequest);
        return "댓글 작성이 완료되었습니다.";
    }

    @Operation(summary = "대댓글 작성")
    @PostMapping("/{postId}/{parentId}")
    public String saveCmt2(@CurrentUser UserPrincipal userPrincipal,
                           @PathVariable Long postId,
                           @PathVariable Long parentId,
                           @RequestBody @Validated CommentRequest commentRequest) {
        commentService.saveCmt2(userPrincipal.getUserId(), postId, parentId, commentRequest);
        return "대댓글 작성이 완료되었습니다.";
    }

    @Operation(summary = "댓글|대댓글 수정")
    @PutMapping("/{cmtId}")
    public String updateCmt(@CurrentUser UserPrincipal userPrincipal,
                            @PathVariable Long cmtId,
                            @RequestBody @Validated CommentRequest commentRequest) {
        commentService.updateCmt(userPrincipal.getUserId(), cmtId, commentRequest);
        return "댓글 수정이 완료되었습니다.";
    }

    @Operation(summary = "댓글|대댓글 삭제")
    @DeleteMapping("/{cmtId}")
    public String deleteCmt(@CurrentUser UserPrincipal userPrincipal,
                            @PathVariable Long cmtId) {
        commentService.deleteCmt(userPrincipal.getUserId(), cmtId);
        return "댓글 삭제가 완료되었습니다.";
    }
}