package com.example.study.member.ui;

import com.example.study.common.ApiSuccessResponse;
import com.example.study.member.command.application.MemberWithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberWithdrawController {

    private final MemberWithdrawService memberWithdrawService;

    @DeleteMapping("/{memberId}")
    public ApiSuccessResponse<Void> withdraw(@PathVariable UUID memberId) {
        memberWithdrawService.withdrawMember(memberId);
        return ApiSuccessResponse.empty();
    }
}
