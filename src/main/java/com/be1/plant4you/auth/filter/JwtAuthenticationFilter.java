package com.be1.plant4you.auth.filter;

import com.be1.plant4you.auth.token.AuthToken;
import com.be1.plant4you.auth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        //프론트에서 보낸 accessToken(백에서 발급) 검증
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String tokenStr = authorizationHeader.substring(7);
            AuthToken token = tokenProvider.convertAuthToken(tokenStr);

            //access token 유효
            if (token.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication); //인증된 사용자 정보 Security Context에 저장
            }
        }

        filterChain.doFilter(request, response);
    }
}

