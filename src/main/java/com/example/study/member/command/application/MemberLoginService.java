package com.example.study.member.command.application;

import com.example.study.member.command.domain.Member;
import com.example.study.member.command.domain.MemberRepository;
import com.example.study.member.command.domain.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member login(MemberLoginDto memberLoginDto) {
        return memberRepository.findByLoginId(memberLoginDto.loginId())
                .filter(member -> passwordEncoder.isMatch(memberLoginDto.password(), member.getPassword()))
                .orElseThrow(InvalidCredentialsException::new);
    }
}
