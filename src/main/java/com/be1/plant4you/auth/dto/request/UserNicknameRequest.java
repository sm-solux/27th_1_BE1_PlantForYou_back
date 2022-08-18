package com.be1.plant4you.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "현재 로그인한 유저 닉네임 변경 시 정보 전달하는 DTO")
@Getter
public class UserNicknameRequest {

    @Schema(description = "유저 닉네임")
    private String userNickname;
}
