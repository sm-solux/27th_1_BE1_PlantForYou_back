package com.be1.plant4you.repository;

import com.be1.plant4you.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
