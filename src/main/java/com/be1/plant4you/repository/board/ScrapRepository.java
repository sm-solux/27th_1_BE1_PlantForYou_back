package com.be1.plant4you.repository.board;

import com.be1.plant4you.domain.board.ScrapId;
import com.be1.plant4you.domain.board.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    @Modifying
    @Query("delete from Scrap s where s.post.id = :postId")
    void deleteAllByPostId(@Param("postId") Long postId);
}