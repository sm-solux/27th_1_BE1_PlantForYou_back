package com.be1.plant4you.controller.board;

import com.be1.plant4you.common.CurrentUser;
import com.be1.plant4you.dto.request.board.CommentRequest;
import com.be1.plant4you.service.board.CommentService;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/posts/cmts")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public String saveCmt(@CurrentUser UserPrincipal userPrincipal,
                          @PathVariable Long postId,
                          @RequestBody CommentRequest commentRequest) {
        Long userId = 0L;
        commentService.saveCmt(userId, postId, commentRequest);
        return "댓글 작성이 완료되었습니다.";
    }

    @PostMapping("/{postId}/{parentId}")
    public String saveCmt2(@CurrentUser UserPrincipal userPrincipal,
                           @PathVariable Long postId,
                           @PathVariable Long parentId,
                           @RequestBody CommentRequest commentRequest) {
        Long userId = 0L;
        commentService.saveCmt2(userId, postId, parentId, commentRequest);
        return "대댓글 작성이 완료되었습니다.";
    }

    @PutMapping("/{cmtId}")
    public String updateCmt(@CurrentUser UserPrincipal userPrincipal,
                            @PathVariable Long cmtId,
                            @RequestBody CommentRequest commentRequest) {
        Long userId = 0L;
        commentService.updateCmt(userId, cmtId, commentRequest);
        return "댓글 수정이 완료되었습니다.";
    }

    @DeleteMapping("/{cmtId}")
    public String deleteCmt(@CurrentUser UserPrincipal userPrincipal,
                         @PathVariable Long cmtId) {
        Long userId = 0L;
        commentService.deleteCmt(userId, cmtId);
        return "댓글 삭제가 완료되었습니다.";
    }
}