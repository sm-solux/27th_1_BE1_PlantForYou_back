package com.be1.plant4you.repository.board.custom;

import com.be1.plant4you.dto.response.board.PostResponse;
import com.be1.plant4you.dto.response.board.PostShortResponse;
import com.be1.plant4you.enumerate.board.PostCat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostShortResponse> findAllByCat(PostCat cat, Pageable pageable);
    Page<PostShortResponse> findAllOrderByCreatedDate(Pageable pageable);
    Page<PostShortResponse> findAllOrderByLikes(Pageable pageable);
    Page<PostShortResponse> findAllByWriterId(Long writerId, Pageable pageable);
    PostResponse findDtoById(Long userId, Long postId);
    Page<PostShortResponse> findAllByUserCmt(Long userId, Pageable pageable);
    Page<PostShortResponse> findAllByUserLikes(Long userId, Pageable pageable);
    Page<PostShortResponse> findAllByUserScrap(Long userId, Pageable pageable);
}
