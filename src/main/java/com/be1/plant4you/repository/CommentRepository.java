package com.be1.plant4you.repository;

import com.be1.plant4you.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select cmt from Comment cmt where cmt.parent is null and cmt.post.id = :postId")
    List<Comment> findAllParentByPostId(@Param("postId") Long postId);

    @Modifying
    @Query("delete from Comment cmt where cmt.parent.id = :parentId")
    void deleteAllByParentId(@Param("parentId") Long parentId);

    @Modifying
    @Query("delete from Comment cmt where cmt.parent.id in :parentIds")
    void deleteAllByParentIdsIn(@Param("parentIds") List<Long> parentIds);

    @Modifying
    @Query("delete from Comment cmt where cmt.parent.id is null and cmt.id in :ids")
    void deleteAllParentByIdsIn(@Param("ids") List<Long> ids);
}