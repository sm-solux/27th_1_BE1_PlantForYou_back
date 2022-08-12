package com.be1.plant4you.board.service;

import com.be1.plant4you.board.domain.Comment;
import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.repository.CommentRepository;
import com.be1.plant4you.board.repository.PostRepository;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.common.exception.CustomException;
import com.be1.plant4you.common.exception.ErrorCode;
import com.be1.plant4you.common.utils.SecurityUtil;
import com.be1.plant4you.common.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserUtil userUtil;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(CommentRequest commentRequest) {
        User user = userUtil.getCurrentUser();
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Comment parent = getParent(commentRequest.getPostId(), commentRequest.getParentId());

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .build();
        comment.changeUser(user);
        comment.changePost(post);
        if (parent != null) comment.changeParent(parent);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequest commentRequest) {
        Comment comment = getComment(SecurityUtil.getCurrentUserId(), commentId, FORBIDDEN_COMMENT_UPDATE);
        comment.changeContent(commentRequest.getContent());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = getComment(SecurityUtil.getCurrentUserId(), commentId, FORBIDDEN_COMMENT_DELETE);

        //댓글일 경우 => 댓글, 연관된 대댓글 모두 삭제
        if (comment.getParent() == null) {
            commentRepository.deleteAllByParentId(comment.getId()); //대댓글 삭제
            commentRepository.delete(comment); //댓글 삭제
        }
        //대댓글일 경우 => update is_delete = true
        else {
            comment.deleteComment2();
        }
    }

    //해당 댓글의 존재 여부 & 유저의 해당 댓글에 대한 권한 확인
    private Comment getComment(Long userId, Long commentId, ErrorCode errorCode) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        if (!Objects.equals(comment.getUser().getId(), userId)) {
            throw new CustomException(errorCode);
        }
        return comment;
    }

    //해당 부모 댓글의 존재 여부 & 해당 게시글의 댓글인지 확인
    private Comment getParent(Long postId, Long parentId) {
        if (parentId == null) return null;
        Comment parent = commentRepository.findParentById(parentId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        if (!Objects.equals(parent.getPost().getId(), postId)) {
            throw new CustomException(NOT_FOUND_COMMENT);
        }
        return parent;
    }
}