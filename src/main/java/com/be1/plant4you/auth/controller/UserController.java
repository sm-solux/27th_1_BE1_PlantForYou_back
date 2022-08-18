package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.request.UserNicknameRequest;
import com.be1.plant4you.auth.dto.response.UserNicknameResponse;
import com.be1.plant4you.auth.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@Api(tags = "유저 API")
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "현재 로그인한 유저 닉네임 조회", description = "로그인 후 메인화면에 '~님 안녕하세요'에 사용")
    @GetMapping("/name")
    public UserNicknameResponse getLoggedInUserNickname() {
        return userService.getUserNickname();
    }

    @Operation(summary = "현재 로그인한 유저 닉네임 변경")
    @PutMapping("/name")
    public ResponseEntity<Void> changeUserNickname(@RequestBody UserNicknameRequest userNicknameRequest) {
        userService.changeUserNickname(userNicknameRequest.getUserNickname());
        return ResponseEntity.status(OK).build();
    }

    @Operation(summary = "유저 탈퇴", description = "일단 유저 관련 정보 모두 삭제하도록 구현")
    @DeleteMapping
    public ResponseEntity<Void> withdraw() {
        userService.withdraw();
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
