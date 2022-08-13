package com.be1.plant4you.board.repository.custom;

import com.be1.plant4you.board.dto.response.PostResponse;
import com.be1.plant4you.board.dto.response.PostListResponse;
import com.be1.plant4you.board.enumerate.PostCat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostListResponse> findAllByCat(PostCat cat, Pageable pageable);
    Page<PostListResponse> findAllOrderByCreatedDate(Pageable pageable);
    Page<PostListResponse> findAllOrderByLikes(Pageable pageable);
    Page<PostListResponse> findAllByWriterId(Long writerId, Pageable pageable);
    PostResponse findDtoById(Long userId, Long postId);
    Page<PostListResponse> findAllByUserComment(Long userId, Pageable pageable);
    Page<PostListResponse> findAllByUserLikes(Long userId, Pageable pageable);
    Page<PostListResponse> findAllByUserScrap(Long userId, Pageable pageable);
}
