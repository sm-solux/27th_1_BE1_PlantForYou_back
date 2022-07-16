package com.be1.plant4you.repository;

import com.be1.plant4you.domain.Post;
import com.be1.plant4you.repository.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
