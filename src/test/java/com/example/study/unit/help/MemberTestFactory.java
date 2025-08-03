package com.example.study.unit.help;

import com.example.study.member.command.domain.Member;
import com.example.study.member.command.domain.MemberStatus;

import java.lang.reflect.Field;

public class MemberTestFactory {

    public static Member createActiveMember() {
        return createMemberWithStatus(MemberStatus.ACTIVE);
    }

    public static Member createSuspendedMember() {
        return createMemberWithStatus(MemberStatus.SUSPENDED);
    }

    public static Member createWithdrawnMember() {
        return createMemberWithStatus(MemberStatus.WITHDRAWN);
    }

    public static Member createMemberWithStatus(MemberStatus status) {
        Member member = new Member(
                "testLoginId",
                "testPassword",
                "test@example.com",
                "테스트회원",
                null // 또는 테스트용 AddressVO
        );

        setStatus(member, status);
        return member;
    }

    private static void setStatus(Member member, MemberStatus status) {
        try {
            Field field = Member.class.getDeclaredField("status");
            field.setAccessible(true);
            field.set(member, status);
        } catch (Exception e) {
            throw new RuntimeException("회원 상태 설정에 실패했습니다.", e);
        }
    }
}
