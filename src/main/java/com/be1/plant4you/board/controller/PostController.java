package com.be1.plant4you.board.controller;

import com.be1.plant4you.board.dto.request.PostRequest;
import com.be1.plant4you.board.dto.response.*;
import com.be1.plant4you.board.enumerate.PostCat;
import com.be1.plant4you.board.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.be1.plant4you.common.validation.ValidationGroup.*;
import static org.springframework.http.HttpStatus.*;

@Api(tags = "게시판 글 API")
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 리스트 조회", description = "카테고리로 게시글 리스트 최신순 조회 | " +
                                                            "전체 게시글 리스트 최신순 or 인기순 조회 | " +
                                                            "현재 로그인한 이용자가 쓴 게시글 리스트 최신순 조회")
    @GetMapping
    public Page<PostListResponse> getPosts(@RequestParam(required = false) PostCat cat,
                                           @RequestParam(required = false) String order,
                                           Pageable pageable) {
        if (cat != null) {
            return postService.getPostsByCat(cat, pageable);
        }
        if (order != null) {
            if (order.equals("new")) {
                return postService.getPostsOrderByNew(pageable);
            } else {
                return postService.getPostsOrderByLikes(pageable);
            }
        }

        return postService.getMyPosts(pageable);
    }

    @Operation(summary = "게시글 상세내용 조회")
    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @Operation(summary = "게시글 등록")
    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody @Validated(PostSave.class) PostRequest postRequest) {
        postService.savePost(postRequest);
        return ResponseEntity.status(CREATED).build();
    }

    @Operation(summary = "게시글 수정 시 게시글만 조회")
    @GetMapping("/{postId}/only")
    public PostOnlyResponse getPostOnly(@PathVariable Long postId) {
        return postService.getPostOnly(postId);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId,
                                           @RequestBody @Validated(PostUpdate.class) PostRequest postRequest) {
        postService.updatePost(postId, postRequest);
        return ResponseEntity.status(OK).build();
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Operation(summary = "현재 로그인한 이용자가 댓글, 대댓글 단 게시글 리스트 최근 댓글 작성 순으로 조회")
    @GetMapping("/comments")
    public Page<PostListResponse> getMyCommentPosts(Pageable pageable) {
        return postService.getMyCommentPosts(pageable);
    }

    @Operation(summary = "현재 로그인한 이용자가 좋아요한 게시글 리스트 최근 좋아요한 순으로 조회")
    @GetMapping("/likes")
    public Page<PostListResponse> getMyLikesPosts(Pageable pageable) {
        return postService.getMyLikesPosts(pageable);
    }

    @Operation(summary = "현재 로그인한 이용자가 스크랩한 게시글 리스트 최근 스크랩한 순으로 조회")
    @GetMapping("/scrap")
    public Page<PostListResponse> getMyScrapPosts(Pageable pageable) {
        return postService.getMyScrapPosts(pageable);
    }

    @Operation(summary = "게시글 좋아요 등록")
    @PostMapping("/{postId}/likes")
    public ResponseEntity<LikesResponse> saveMyLikesPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(postService.saveLikesPost(postId));
    }

    @Operation(summary = "게시글 스크랩 등록")
    @PostMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> saveMyScrapPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(postService.saveScrapPost(postId));
    }

    @Operation(summary = "게시글 좋아요 취소")
    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<LikesResponse> deleteMyLikesPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(postService.deleteLikesPost(postId));
    }

    @Operation(summary = "게시글 스크랩 취소")
    @DeleteMapping("/{postId}/scrap")
    public ResponseEntity<ScrapResponse> deleteMyScrapPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK).body(postService.deleteScrapPost(postId));
    }
}