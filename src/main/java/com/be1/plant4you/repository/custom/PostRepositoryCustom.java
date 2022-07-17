package com.be1.plant4you.repository.custom;

import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;
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
