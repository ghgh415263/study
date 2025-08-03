package com.example.study.member.command.domain;

/**
 * 회원의 상태를 나타내는 열거형입니다.
 */
public enum MemberStatus {
    /** 정상 상태의 회원 */
    ACTIVE,

    /** 정지 상태의 회원 */
    SUSPENDED,

    /** 탈퇴 상태의 회원 */
    WITHDRAWN
}
