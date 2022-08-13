package com.be1.plant4you.common.utils;

import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.be1.plant4you.common.exception.ErrorCode.*;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.findById(SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    }
}
