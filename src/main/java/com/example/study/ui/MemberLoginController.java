package com.example.study.ui;

import com.example.study.application.MemberLoginService;
import com.example.study.domain.member.Member;
import com.example.study.webcommon.ApiSuccessResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class MemberLoginController {

    private final MemberLoginService memberLoginService;

    @PostMapping
    public ApiSuccessResponse<Void> login(
            @Valid @RequestBody MemberLoginDto dto,
            HttpSession session
    ) {
        Member loginedMember = memberLoginService.login(dto);
        session.setAttribute("loginId", loginedMember.getId().toString());

        return ApiSuccessResponse.empty();
    }
}