package com.be1.plant4you.common.utils;

import com.be1.plant4you.auth.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("Security Context 에 인증 정보가 없습니다.");
        }
        return ((UserPrincipal) authentication.getPrincipal()).getUserId();
    }
}
