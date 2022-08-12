package com.be1.plant4you.auth.controller;

import com.be1.plant4you.auth.dto.response.UserNameResponse;
import com.be1.plant4you.auth.service.UserService;
import com.be1.plant4you.common.auth.CurrentUser;
import com.be1.plant4you.common.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/name")
    public UserNameResponse getLoggedInUserName(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getUserName(userPrincipal.getUserId());
    }

    @DeleteMapping
    public String withdraw(@CurrentUser UserPrincipal userPrincipal) {
        userService.withdraw(userPrincipal.getUserId());
        return "탈퇴가 완료되었습니다.";
    }
}
