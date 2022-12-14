package com.be1.plant4you.board.service;

import com.be1.plant4you.board.domain.Comment;
import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.dto.response.CommentResponse;
import com.be1.plant4you.board.repository.CommentRepository;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.common.exception.CustomException;
import com.be1.plant4you.common.exception.ErrorCode;
import com.be1.plant4you.common.utils.SecurityUtil;
import com.be1.plant4you.common.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final UserUtil userUtil;

    @Transactional
    public List<CommentResponse> saveComment(CommentRequest commentRequest) {
        User user = userUtil.getCurrentUser();
        Post post = postService._getPost(commentRequest.getPostId());
        Comment parent = _getParent(commentRequest.getPostId(), commentRequest.getParentId());

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .build();
        comment.changeUser(user);
        comment.changePost(post);
        if (parent != null) comment.changeParent(parent);
        commentRepository.save(comment);

        return commentRepository.findCommentListByPostId(user.getId(), commentRequest.getPostId());
    }

    @Transactional
    public List<CommentResponse> updateComment(Long commentId, CommentRequest commentRequest) {
        Long userId = SecurityUtil.getCurrentUserId();
        Comment comment = _getComment(userId, commentId, FORBIDDEN_COMMENT_UPDATE);
        comment.changeContent(commentRequest.getContent());

        return commentRepository.findCommentListByPostId(userId, comment.getPost().getId());
    }

    @Transactional
    public List<CommentResponse> deleteComment(Long commentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        Comment comment = _getComment(userId, commentId, FORBIDDEN_COMMENT_DELETE);
        //????????? ?????? => ??????, ????????? ????????? ?????? ??????
        if (comment.getParent() == null) {
            commentRepository.deleteAllByParentId(comment.getId()); //????????? ??????
            commentRepository.delete(comment); //?????? ??????
        }
        //???????????? ?????? => update is_delete = true
        else {
            comment.deleteComment2();
        }

        return commentRepository.findCommentListByPostId(userId, comment.getPost().getId());
    }

    //?????? ????????? ?????? ?????? & ????????? ?????? ????????? ?????? ?????? ??????
    private Comment _getComment(Long userId, Long commentId, ErrorCode errorCode) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        if (!Objects.equals(comment.getUser().getId(), userId)) {
            throw new CustomException(errorCode);
        }
        return comment;
    }

    //?????? ?????? ????????? ?????? ?????? & ?????? ???????????? ???????????? ??????
    private Comment _getParent(Long postId, Long parentId) {
        if (parentId == null) return null;
        Comment parent = commentRepository.findParentById(parentId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        if (!Objects.equals(parent.getPost().getId(), postId)) {
            throw new CustomException(NOT_FOUND_COMMENT);
        }
        return parent;
    }
}