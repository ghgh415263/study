package com.example.study.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberCheckService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 ID가 이미 존재하는지 확인
     *
     * @param loginId 검사할 로그인 ID
     * @return 중복이면 true, 아니면 false
     */
    public boolean isDuplicateUsername(String loginId) {
        return memberRepository.findByLoginId(loginId).isPresent();
    }

}
