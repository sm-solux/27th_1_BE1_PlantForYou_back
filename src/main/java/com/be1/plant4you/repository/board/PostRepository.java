package com.be1.plant4you.repository.board;

import com.be1.plant4you.domain.board.Post;
import com.be1.plant4you.repository.board.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
