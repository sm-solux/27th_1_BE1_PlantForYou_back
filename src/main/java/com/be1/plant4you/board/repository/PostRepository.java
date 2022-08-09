package com.be1.plant4you.board.repository;

import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.repository.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
