package com.example.study.unit;

import com.example.study.member.command.domain.InvalidMemberStateException;
import com.example.study.member.command.domain.Member;
import com.example.study.member.command.domain.MemberStatus;
import com.example.study.unit.help.MemberTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberTest {

    @Test
    @DisplayName("withdraw는 ACTIVE 상태일 때 정상 수행된다")
    void withdraw_succeeds_when_status_is_active() {
        Member member = MemberTestFactory.createActiveMember();

        assertThatCode(member::withdraw)
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("withdraw는 WITHDRAWN 상태일 때 WITHDRAWN 발생")
    void withdraw_throws_when_status_is_withdrawn() {
        Member member = MemberTestFactory.createWithdrawnMember();

        assertThatThrownBy(member::withdraw)
                .isInstanceOf(InvalidMemberStateException.class)
                .hasMessageContaining("현재 상태(WITHDRAWN)");
    }

    @Test
    @DisplayName("withdraw는 SUSPENDED 상태일 때 SUSPENDED 발생")
    void withdraw_throws_when_status_is_suspended() {
        Member member = MemberTestFactory.createSuspendedMember();

        assertThatThrownBy(member::withdraw)
                .isInstanceOf(InvalidMemberStateException.class)
                .hasMessageContaining("현재 상태(SUSPENDED)");
    }

    @Test
    @DisplayName("정상 상태의 회원은 suspend 가능")
    void suspend_success_when_status_is_active() {
        Member member = MemberTestFactory.createActiveMember();

        member.suspend();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.SUSPENDED);
    }

    @Test
    @DisplayName("정상 상태가 아니면 suspend 시 예외 발생")
    void suspend_throws_exception_when_status_not_active() {
        Member member = MemberTestFactory.createWithdrawnMember();

        assertThatThrownBy(member::suspend)
                .isInstanceOf(InvalidMemberStateException.class)
                .hasMessageContaining("현재 상태(WITHDRAWN)");
    }

    @Test
    @DisplayName("탈퇴 상태가 아니면 activate 가능")
    void activate_success_when_status_not_withdrawn() {
        Member member = MemberTestFactory.createSuspendedMember();

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    @DisplayName("탈퇴 상태면 activate 시 예외 발생")
    void activate_throws_exception_when_status_withdrawn() {
        Member member = MemberTestFactory.createWithdrawnMember();

        assertThatThrownBy(member::activate)
                .isInstanceOf(InvalidMemberStateException.class)
                .hasMessageContaining("현재 상태(WITHDRAWN)");
    }
}
