package com.be1.plant4you.repository;

import com.be1.plant4you.common.ScrapId;
import com.be1.plant4you.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, ScrapId> {

    @Query("select s from Scrap s where s.post.id = :postId")
    List<Scrap> findAllByPostId(@Param("postId") Long postId);
}
