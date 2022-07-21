package com.be1.plant4you.controller.board;

import com.be1.plant4you.common.CurrentUser;
import com.be1.plant4you.dto.request.board.PostRequest;
import com.be1.plant4you.dto.request.board.PostUpdateRequest;
import com.be1.plant4you.dto.response.board.PostResponse;
import com.be1.plant4you.dto.response.board.PostListResponse;
import com.be1.plant4you.enumerate.board.PostCat;
import com.be1.plant4you.service.board.PostService;
import com.sun.security.auth.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시판 글 API")
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 리스트 조회", description = "카테고리로 게시글 리스트 최신순 조회 | " +
                                                            "전체 게시글 리스트 최신순 or 인기순 조회 | " +
                                                            "현재 로그인한 이용자가 쓴 게시글 리스트 최신순 조회")
    @GetMapping
    public Page<PostListResponse> getPosts(@CurrentUser UserPrincipal userPrincipal,
                                           @RequestParam(required = false) PostCat cat,
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

        Long userId = 0L;
        return postService.getMyPosts(userId, pageable);
    }

    @Operation(summary = "게시글 상세내용 조회")
    @GetMapping("/{postId}")
    public PostResponse getPost(@CurrentUser UserPrincipal userPrincipal,
                                @PathVariable Long postId) {
        Long userId = 0L;
        return postService.getPost(userId, postId); //현재 로그인한 이용자가 좋아요, 스크랩한 글인지 여부 확인 위해 userId 전달
    }

    @Operation(summary = "게시글 등록")
    @PostMapping
    public String uploadPost(@CurrentUser UserPrincipal userPrincipal,
                             @RequestBody PostRequest postRequest) {
        Long userId = 0L;
        postService.upload(userId, postRequest);
        return "글 등록이 완료되었습니다.";
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public String modifyPost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable Long postId,
                             @RequestBody PostUpdateRequest postUpdateRequest) {
        Long userId = 0L;
        postService.updatePost(userId, postId, postUpdateRequest);
        return "글 수정이 완료되었습니다.";
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public String deletePost(@CurrentUser UserPrincipal userPrincipal,
                             @PathVariable Long postId) {
        Long userId = 0L;
        postService.deletePost(userId, postId);
        return "글 삭제가 완료되었습니다.";
    }

    @Operation(summary = "현재 로그인한 이용자가 댓글, 대댓글 단 게시글 리스트 최근 댓글 작성 순으로 조회")
    @GetMapping("/cmt")
    public Page<PostListResponse> getMyCmtPosts(@CurrentUser UserPrincipal userPrincipal,
                                                Pageable pageable) {
        Long userId = 0L;
        return postService.getMyCmtPosts(userId, pageable);
    }

    @Operation(summary = "현재 로그인한 이용자가 좋아요한 게시글 리스트 최근 좋아요한 순으로 조회")
    @GetMapping("/likes")
    public Page<PostListResponse> getMyLikesPosts(@CurrentUser UserPrincipal userPrincipal,
                                                  Pageable pageable) {
        Long userId = 0L;
        return postService.getMyLikesPosts(userId, pageable);
    }

    @Operation(summary = "현재 로그인한 이용자가 스크랩한 게시글 리스트 최근 스크랩한 순으로 조회")
    @GetMapping("/scrap")
    public Page<PostListResponse> getMyScrapPosts(@CurrentUser UserPrincipal userPrincipal,
                                                  Pageable pageable) {
        Long userId = 0L;
        return postService.getMyScrapPosts(userId, pageable);
    }

    @Operation(summary = "게시글 좋아요 등록")
    @PostMapping("/likes/{postId}")
    public String saveMyLikesPost(@CurrentUser UserPrincipal userPrincipal,
                                  @PathVariable Long postId) {
        Long userId = 0L;
        postService.saveLikesPost(userId, postId);
        return "게시글을 좋아요 하였습니다.";
    }

    @Operation(summary = "게시글 스크랩 등록")
    @PostMapping("/scrap/{postId}")
    public String saveMyScrapPost(@CurrentUser UserPrincipal userPrincipal,
                                  @PathVariable Long postId) {
        Long userId = 0L;
        postService.saveScrapPost(userId, postId);
        return "게시글을 스크랩하였습니다.";
    }

    @Operation(summary = "게시글 좋아요 취소")
    @DeleteMapping("/likes/{postId}")
    public String deleteMyLikesPost(@CurrentUser UserPrincipal userPrincipal,
                                    @PathVariable Long postId) {
        Long userId = 0L;
        postService.deleteLikesPost(userId, postId);
        return "게시글 좋아요를 취소하였습니다.";
    }

    @Operation(summary = "게시글 스크랩 취소")
    @DeleteMapping("/scrap/{postId}")
    public String deleteMyScrapPost(@CurrentUser UserPrincipal userPrincipal,
                                    @PathVariable Long postId) {
        Long userId = 0L;
        postService.deleteScrapPost(userId, postId);
        return "게시글 스크랩을 취소하였습니다.";
    }
}