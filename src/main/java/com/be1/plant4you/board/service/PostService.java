package com.be1.plant4you.board.service;

import com.be1.plant4you.auth.domain.User;
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
import com.be1.plant4you.common.exception.ErrorCode;
import com.be1.plant4you.common.utils.SecurityUtil;
import com.be1.plant4you.common.utils.UserUtil;
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

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;
    private final ScrapRepository scrapRepository;
    private final UserUtil userUtil;

    public Page<PostListResponse> getPostsByCat(PostCat cat, Pageable pageable) {
        return postRepository.findAllByCat(cat, pageable);
    }

    public Page<PostListResponse> getPostsOrderByNew(Pageable pageable) {
        return postRepository.findAllOrderByCreatedDate(pageable);
    }

    public Page<PostListResponse> getPostsOrderByLikes(Pageable pageable) {
        return postRepository.findAllOrderByLikes(pageable);
    }

    public Page<PostListResponse> getMyPosts(Pageable pageable) {
        return postRepository.findAllByWriterId(SecurityUtil.getCurrentUserId(), pageable);
    }

    public PostResponse getPost(Long postId) {
        _getPost(postId);
        return postRepository.findDtoById(SecurityUtil.getCurrentUserId(), postId);
    }

    @Transactional
    public void upload(PostRequest postRequest) {
        User user = userUtil.getCurrentUser();
        Post post = Post.builder()
                .cat(postRequest.getCat())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .build();
        post.changeUser(user); //글 작성자

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, PostRequest postRequest) {
        Post post = _getPost(SecurityUtil.getCurrentUserId(), postId, FORBIDDEN_POST_UPDATE);
        post.update(postRequest.getTitle(), postRequest.getContent());
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = _getPost(SecurityUtil.getCurrentUserId(), postId, FORBIDDEN_POST_DELETE);
        List<Comment> parentList = commentRepository.findAllParentByPostId(postId);
        List<Long> parentIds = parentList.stream().map(Comment::getId).collect(Collectors.toList());

        commentRepository.deleteAllByParentIdsIn(parentIds); //대댓글 삭제
        commentRepository.deleteAllParentByIdsIn(parentIds); //댓글 삭제
        //Likes, Scrap 테이블에서 해당 글 삭제
        likesRepository.deleteAllByPostId(postId);
        scrapRepository.deleteAllByPostId(postId);
        postRepository.delete(post);
    }

    public Page<PostListResponse> getMyCommentPosts(Pageable pageable) {
        return postRepository.findAllByUserComment(SecurityUtil.getCurrentUserId(), pageable);
    }

    public Page<PostListResponse> getMyLikesPosts(Pageable pageable) {
        return postRepository.findAllByUserLikes(SecurityUtil.getCurrentUserId(), pageable);
    }

    public Page<PostListResponse> getMyScrapPosts(Pageable pageable) {
        return postRepository.findAllByUserScrap(SecurityUtil.getCurrentUserId(), pageable);
    }

    @Transactional
    public void saveLikesPost(Long postId) {
        User user = userUtil.getCurrentUser();
        Post post = _getPost(postId);
        Optional<Likes> likesOptional = likesRepository.findById(
                LikesId.builder()
                        .userId(user.getId())
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
    public void saveScrapPost(Long postId) {
        User user = userUtil.getCurrentUser();
        Post post = _getPost(postId);
        Optional<Scrap> scrapOptional = scrapRepository.findById(
                ScrapId.builder()
                        .userId(user.getId())
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
    public void deleteLikesPost(Long postId) {
        Post post = _getPost(postId);
        Likes likes = likesRepository.findById(
                LikesId.builder()
                        .userId(SecurityUtil.getCurrentUserId())
                        .postId(postId)
                        .build()
        ).orElseThrow(() -> new CustomException(INVALID_POST_LIKES));

        likesRepository.delete(likes);
        post.minusLikes();
    }

    @Transactional
    public void deleteScrapPost(Long postId) {
        Post post = _getPost(postId);
        Scrap scrap = scrapRepository.findById(
                ScrapId.builder()
                        .userId(SecurityUtil.getCurrentUserId())
                        .postId(postId)
                        .build()
        ).orElseThrow(() -> new CustomException(INVALID_POST_SCRAP));

        scrapRepository.delete(scrap);
        post.minusScraps();
    }

    //해당 글의 존재 여부 확인
    protected Post _getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
    }

    //해당 글의 존재 여부 & 유저의 해당 글에 대한 권한 확인
    private Post _getPost(Long userId, Long postId, ErrorCode errorCode) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new CustomException(NOT_FOUND_POST));
        if (!Objects.equals(post.getUser().getId(), userId)) {
            throw new CustomException(errorCode);
        }
        return post;
    }
}