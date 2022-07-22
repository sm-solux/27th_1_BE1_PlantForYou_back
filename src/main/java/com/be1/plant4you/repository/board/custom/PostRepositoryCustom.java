package com.be1.plant4you.repository.board.custom;

import com.be1.plant4you.dto.response.board.PostResponse;
import com.be1.plant4you.dto.response.board.PostListResponse;
import com.be1.plant4you.enumerate.board.PostCat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostListResponse> findAllByCat(PostCat cat, Pageable pageable);
    Page<PostListResponse> findAllOrderByCreatedDate(Pageable pageable);
    Page<PostListResponse> findAllOrderByLikes(Pageable pageable);
    Page<PostListResponse> findAllByWriterId(Long writerId, Pageable pageable);
    PostResponse findDtoById(Long userId, Long postId);
    Page<PostListResponse> findAllByUserCmt(Long userId, Pageable pageable);
    Page<PostListResponse> findAllByUserLikes(Long userId, Pageable pageable);
    Page<PostListResponse> findAllByUserScrap(Long userId, Pageable pageable);
}
