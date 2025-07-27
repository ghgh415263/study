package com.example.study.member.command.application;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginDto(

        @NotBlank(message = "로그인 아이디는 필수입니다.")
        String loginId,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password

) {}