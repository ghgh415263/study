package com.example.study.application;

import com.example.study.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원 가입 서비스를 담당하는 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final PasswordMeter passwordMeter;

    private final MemberCheckService memberCheckService;

    /**
     * 회원 가입 처리 메서드
     * 1. 비밀번호 강도 확인
     * 2. 로그인 ID 중복 검사
     * 3. Member 객체 생성 후 저장
     *
     * @param memberJoinDto 가입 요청 데이터
     * @return 저장된 회원의 ID
     */
    @Transactional
    public Long saveMember(MemberJoinDto memberJoinDto) {

        if (passwordMeter.isWeak(memberJoinDto.getPassword()))
            throw new WeakPasswordException();

        if (memberCheckService.isDuplicateUsername(memberJoinDto.getLoginId()))
            throw new DuplicatedLoginIdException();

        Member member = new Member(
                memberJoinDto.getLoginId(),
                passwordEncoder.hashPassword(memberJoinDto.getPassword()),
                memberJoinDto.getEmail(),
                memberJoinDto.getName());

        return memberRepository.save(member).getId();
    }
}