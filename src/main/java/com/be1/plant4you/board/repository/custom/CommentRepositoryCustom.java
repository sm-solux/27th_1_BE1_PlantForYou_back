package com.be1.plant4you.board.repository.custom;

import com.be1.plant4you.board.dto.response.CommentResponse;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentResponse> findCommentListByPostId(Long userId, Long postId);
}
