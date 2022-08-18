package com.be1.plant4you.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "현재 로그인한 유저 이름 반환하는 DTO")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNicknameResponse {

    @Schema(description = "유저 닉네임")
    private String userNickname;
}
