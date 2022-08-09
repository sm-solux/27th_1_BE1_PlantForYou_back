package com.be1.plant4you.board.service;

import com.be1.plant4you.board.domain.Comment;
import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.repository.CommentRepository;
import com.be1.plant4you.board.repository.PostRepository;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.board.dto.request.CommentRequest;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveCmt(Long userId, Long postId, CommentRequest commentRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .build();
        comment.changeUser(user);
        comment.changePost(post);
        commentRepository.save(comment);
    }

    @Transactional
    public void saveCmt2(Long userId, Long postId, Long parentId, CommentRequest commentRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Comment parent = commentRepository.findParentById(parentId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));
        if (!Objects.equals(parent.getPost().getId(), postId)) {
            throw new CustomException(NOT_FOUND_COMMENT);
        }

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .build();
        comment.changeUser(user);
        comment.changePost(post);
        comment.changeParent(parent);
        commentRepository.save(comment);

    }

    @Transactional
    public void updateCmt(Long userId, Long cmtId, CommentRequest commentRequest) {
        Comment comment = commentRepository.findById(cmtId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 수정 가능
        if (Objects.equals(comment.getUser().getId(), userId)) {
            comment.changeContent(commentRequest.getContent());
        }
        else {
            throw new CustomException(FORBIDDEN_COMMENT_UPDATE);
        }
    }

    @Transactional
    public void deleteCmt(Long userId, Long cmtId) {
        Comment comment = commentRepository.findById(cmtId).orElseThrow(() -> new CustomException(NOT_FOUND_COMMENT));

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 삭제 가능
        if (Objects.equals(comment.getUser().getId(), userId)) {
            //댓글이 경우 => 댓글, 연관된 대댓글 모두 삭제
            if (comment.getParent() == null) {
                commentRepository.deleteAllByParentId(comment.getId()); //대댓글 삭제
                commentRepository.delete(comment); //댓글 삭제
            }
            //대댓글일 경우 => update is_delete = true
            else {
                comment.deleteCmt2();
            }
        }
        else {
            throw new CustomException(FORBIDDEN_COMMENT_DELETE);
        }
    }
}