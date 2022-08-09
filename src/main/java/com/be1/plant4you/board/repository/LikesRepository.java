package com.be1.plant4you.board.repository;

import com.be1.plant4you.board.domain.LikesId;
import com.be1.plant4you.board.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, LikesId> {

    @Modifying
    @Query("delete from Likes l where l.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);
}