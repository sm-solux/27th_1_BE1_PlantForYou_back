package com.be1.plant4you.auth.repository;

import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.oauth.enumerate.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByProviderAndProviderId(Provider provider, String providerId);

    @Query("select u.nickName from User u where u.id = :userId")
    String findNameById(@Param("userId") Long userId);
}
