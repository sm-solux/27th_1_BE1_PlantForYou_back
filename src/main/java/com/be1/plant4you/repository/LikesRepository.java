package com.be1.plant4you.repository;

import com.be1.plant4you.common.LikesId;
import com.be1.plant4you.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, LikesId> {

    @Query("select l from Likes l where l.post.id = :postId")
    List<Likes> findAllByPostId(@Param("postId") Long postId);
}
