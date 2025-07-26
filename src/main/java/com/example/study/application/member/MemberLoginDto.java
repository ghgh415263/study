package com.example.study.application.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginDto {

    @NotBlank(message = "로그인 아이디는 필수입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}