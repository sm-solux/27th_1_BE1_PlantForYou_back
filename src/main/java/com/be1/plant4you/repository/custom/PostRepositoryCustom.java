package com.be1.plant4you.repository.custom;

import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;

import java.util.List;

public interface PostRepositoryCustom {

    List<PostShortResponse> findAllByCat(PostCat cat);
    List<PostShortResponse> findAllOrderByCreatedDate();
    List<PostShortResponse> findAllOrderByLikes();
    List<PostShortResponse> findAllByWriterId(Long writerId);
    PostResponse findDtoById(Long userId, Long postId);
    List<PostShortResponse> findAllByUserCmt(Long userId);
    List<PostShortResponse> findAllByUserLikes(Long userId);
    List<PostShortResponse> findAllByUserScrap(Long userId);
}
