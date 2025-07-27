package com.example.study.ui.member;

import com.example.study.application.member.MemberJoinDto;
import com.example.study.application.member.MemberJoinService;
import com.example.study.webcommon.ApiSuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class MemberJoinController {

    private final MemberJoinService memberJoinService;

    @PostMapping
    public ApiSuccessResponse<Void> join(@Validated @RequestBody MemberJoinDto dto) {
        memberJoinService.saveMember(dto);
        return ApiSuccessResponse.empty();
    }
}
