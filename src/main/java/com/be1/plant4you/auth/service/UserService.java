package com.be1.plant4you.auth.service;

import com.be1.plant4you.auth.domain.RefreshToken;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.auth.dto.response.UserNameResponse;
import com.be1.plant4you.auth.repository.RefreshTokenRepository;
import com.be1.plant4you.auth.repository.UserRepository;
import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.service.PostService;
import com.be1.plant4you.common.exception.CustomException;
import com.be1.plant4you.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PostService postService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public UserNameResponse getUserName(Long userId) {
        String userName = userRepository.findNameById(userId);
        return UserNameResponse.builder()
                .userName(userName)
                .build();
    }

    @Transactional
    public void withdraw(Long userId) { //탈퇴 시 해당 유저와 관련된 정보 일단 모두 삭제하는 것으로 구현
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        List<Long> postIds = user.getPostList().stream().map(Post::getId).collect(Collectors.toList()); //쿼리 나감

        for (Long postId : postIds) {
            postService.deletePost(userId, postId);
        }
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByKey(userId);
        refreshTokenOptional.ifPresent(refreshTokenRepository::delete);
        userRepository.delete(user);
    }
}
