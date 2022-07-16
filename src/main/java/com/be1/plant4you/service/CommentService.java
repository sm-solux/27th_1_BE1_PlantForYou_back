package com.be1.plant4you.service;

import com.be1.plant4you.domain.Comment;
import com.be1.plant4you.domain.Post;
import com.be1.plant4you.domain.User;
import com.be1.plant4you.dto.request.CommentRequest;
import com.be1.plant4you.repository.CommentRepository;
import com.be1.plant4you.repository.PostRepository;
import com.be1.plant4you.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    }

    @Transactional
    public void saveCmt2(Long userId, Long postId, Long parentId, CommentRequest commentRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<Comment> parentOptional = commentRepository.findById(parentId);

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
    }

    @Transactional
    public void updateCmt(Long userId, Long cmtId, CommentRequest commentRequest) {
        Optional<Comment> commentOptional = commentRepository.findById(cmtId);

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 수정 가능
        if (commentOptional.isPresent() && Objects.equals(commentOptional.get().getUser().getId(), userId)) {
            Comment comment = commentOptional.get();
            comment.changeContent(commentRequest.getContent());
        }
    }

    @Transactional
    public void deleteCmt(Long userId, Long cmtId) {
        Optional<Comment> commentOptional = commentRepository.findById(cmtId);

        //댓글이 존재하면서, 해당 댓글이 현재 로그인한 이용자가 쓴 댓글일 경우에만 삭제 가능
        if (commentOptional.isPresent() && Objects.equals(commentOptional.get().getUser().getId(), userId)) {
            Comment comment = commentOptional.get();

            //댓글이 경우 => 댓글, 연관된 대댓글 모두 삭제
            if (comment.getParent() == null) {
                List<Comment> childList = commentRepository.findAllByParentId(cmtId);

                commentRepository.deleteAll(childList); //나중에 orphanRemoval = true 적용하기
                commentRepository.delete(comment);
            }
            //대댓글일 경우 => is_delete = true
            else {
                comment.deleteCmt2();
            }
        }
    }
}
