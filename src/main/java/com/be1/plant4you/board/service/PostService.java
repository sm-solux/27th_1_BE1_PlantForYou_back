package com.be1.plant4you.board.service;

import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.board.domain.*;
import com.be1.plant4you.board.dto.request.PostRequest;
import com.be1.plant4you.board.dto.response.PostListResponse;
import com.be1.plant4you.board.dto.response.PostResponse;
import com.be1.plant4you.board.repository.CommentRepository;
import com.be1.plant4you.board.repository.LikesRepository;
import com.be1.plant4you.board.repository.PostRepository;
import com.be1.plant4you.board.repository.ScrapRepository;
import com.be1.plant4you.board.enumerate.PostCat;
import com.be1.plant4you.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;
    private final ScrapRepository scrapRepository;

    public Page<PostListResponse> getPostsByCat(PostCat cat, Pageable pageable) {
        return postRepository.findAllByCat(cat, pageable);
    }

    public Page<PostListResponse> getPostsOrderByNew(Pageable pageable) {
        return postRepository.findAllOrderByCreatedDate(pageable);
    }

    public Page<PostListResponse> getPostsOrderByLikes(Pageable pageable) {
        return postRepository.findAllOrderByLikes(pageable);
    }

    public Page<PostListResponse> getMyPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByWriterId(userId, pageable);
    }

    public PostResponse getPost(Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        return postRepository.findDtoById(userId, postId);
    }

    @Transactional
    public void upload(Long userId, PostRequest postRequest) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Post post = Post.builder()
                    .cat(postRequest.getCat())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .build();
            post.changeUser(user); //글 작성자

            postRepository.save(post);
        }
    }

    @Transactional
    public void updatePost(Long userId, Long postId, PostRequest postRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 수정 가능
        if (Objects.equals(post.getUser().getId(), userId)) {
            post.update(postRequest.getTitle(), postRequest.getContent());
        }
        else {
            throw new CustomException(FORBIDDEN_POST_UPDATE);
        }
    }

    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));

        //글이 존재하면서, 해당 글이 현재 로그인한 이용자가 쓴 글일 경우에만 삭제 가능
        if (Objects.equals(post.getUser().getId(), userId)) {
            List<Comment> parentList = commentRepository.findAllParentByPostId(postId);
            List<Long> parentIds = parentList.stream().map(Comment::getId).collect(Collectors.toList());

            commentRepository.deleteAllByParentIdsIn(parentIds); //대댓글 삭제
            commentRepository.deleteAllParentByIdsIn(parentIds); //댓글 삭제
            //Likes, Scrap 테이블에서 해당 글 삭제
            likesRepository.deleteAllByPostId(postId);
            scrapRepository.deleteAllByPostId(postId);
            postRepository.delete(post);
        }
        else {
            throw new CustomException(FORBIDDEN_POST_DELETE);
        }
    }

    public Page<PostListResponse> getMyCmtPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserCmt(userId, pageable);
    }

    public Page<PostListResponse> getMyLikesPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserLikes(userId, pageable);
    }

    public Page<PostListResponse> getMyScrapPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByUserScrap(userId, pageable);
    }

    @Transactional
    public void saveLikesPost(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Optional<Likes> likesOptional = likesRepository.findById(
                LikesId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if(likesOptional.isEmpty()) {
            Likes likes = Likes.builder()
                    .user(user)
                    .post(post)
                    .build();

            likesRepository.save(likes);
            post.plusLikes();
        }
        else {
            throw new CustomException(ALREADY_POST_LIKES);
        }
    }

    @Transactional
    public void saveScrapPost(Long userId, Long postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Optional<Scrap> scrapOptional = scrapRepository.findById(
                ScrapId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        );

        if(scrapOptional.isEmpty()) {
            Scrap scrap = Scrap.builder()
                    .user(user)
                    .post(post)
                    .build();

            scrapRepository.save(scrap);
            post.plusScraps();
        }
        else {
            throw new CustomException(ALREADY_POST_SCRAP);
        }
    }

    @Transactional
    public void deleteLikesPost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Likes likes = likesRepository.findById(
                LikesId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        ).orElseThrow(() -> new CustomException(INVALID_POST_LIKES));

        likesRepository.delete(likes);
        post.minusLikes();
    }

    @Transactional
    public void deleteScrapPost(Long userId, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        Scrap scrap = scrapRepository.findById(
                ScrapId.builder()
                        .userId(userId)
                        .postId(postId)
                        .build()
        ).orElseThrow(() -> new CustomException(INVALID_POST_SCRAP));

        scrapRepository.delete(scrap);
        post.minusScraps();
    }
}