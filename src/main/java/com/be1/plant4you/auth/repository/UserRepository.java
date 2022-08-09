package com.be1.plant4you.auth.repository;

import com.be1.plant4you.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
