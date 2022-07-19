package com.be1.plant4you.service;

import com.be1.plant4you.domain.Comment;
import com.be1.plant4you.domain.Post;
import com.be1.plant4you.domain.User;
import com.be1.plant4you.dto.request.CommentRequest;
import com.be1.plant4you.exception.NotMyCommentException;
import com.be1.plant4you.exception.WrongCommentIdException;
import com.be1.plant4you.exception.WrongPostIdException;
import com.be1.plant4you.repository.CommentRepository;
import com.be1.plant4you.repository.PostRepository;
import com.be1.plant4you.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveCmt(Long userId, Long postId, CommentRequest commentRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);

        if (userOptional.isPresent() && postOptional.isPresent()) {
            User user = userOptional.get();
            Post post = postOptional.get();

            Comment comment = Comment.builder()
                    .content(commentRequest.getContent())
                    .build();
            comment.changeUser(user);
            comment.changePost(post);

            commentRepository.save(comment);
        }
        else if (postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
    }

    @Transactional
    public void saveCmt2(Long userId, Long postId, Long parentId, CommentRequest commentRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Comment> parentOptional = commentRepository.findParentById(parentId);

        if (userOptional.isPresent() && postOptional.isPresent() && parentOptional.isPresent()) {
            User user = userOptional.get();
            Post post = postOptional.get();
            Comment parent = parentOptional.get();

            Comment comment = Comment.builder()
                    .content(commentRequest.getContent())
                    .build();
            comment.changeUser(user);
            comment.changePost(post);
            comment.changeParent(parent);

            commentRepository.save(comment);
        }
        else if (postOptional.isEmpty()) {
            throw new WrongPostIdException("존재하지 않는 게시글입니다.");
        }
        else if (parentOptional.isEmpty()) {
            Optional<Comment> commentOptional = commentRepository.findById(parentId);
            if (commentOptional.isPresent()) {
                throw new WrongCommentIdException("댓글에만 대댓글 작성이 가능합니다.");
            }
            else {
                throw new WrongCommentIdException("존재하지 않는 댓글입니다.");
            }
        }
    }

    @Transactional
    public void updateCmt(Long userId, Long cmtId, CommentRequest commentRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(cmtId);

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 수정 가능
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            if (Objects.equals(comment.getUser().getId(), userId)) {
                comment.changeContent(commentRequest.getContent());
            }
            else {
                throw new NotMyCommentException("현재 로그인한 이용자가 작성한 댓글이 아닙니다. " +
                        "자신이 작성한 댓글만 수정 가능합니다.");
            }
        }
        else {
            throw new WrongCommentIdException("존재하지 않는 댓글입니다.");
        }
    }

    @Transactional
    public void deleteCmt(Long userId, Long cmtId) {
        Optional<Comment> commentOptional = commentRepository.findById(cmtId);

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 삭제 가능
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
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
                throw new NotMyCommentException("현재 로그인한 이용자가 작성한 댓글이 아닙니다. " +
                        "자신이 작성한 댓글만 삭제 가능합니다.");
            }
        }
        else {
            throw new WrongCommentIdException("존재하지 않는 댓글입니다.");
        }
    }
}