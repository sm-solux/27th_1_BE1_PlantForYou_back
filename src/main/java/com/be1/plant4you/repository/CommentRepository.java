package com.be1.plant4you.repository;

import com.be1.plant4you.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select cmt from Comment cmt where cmt.parent.id = :parentId")
    List<Comment> findAllByParentId(@Param("parentId") Long parentId);
}
