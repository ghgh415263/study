package com.example.study.application;

import com.example.study.domain.Member;
import com.example.study.domain.MemberRepository;
import com.example.study.domain.PasswordEncoder;
import com.example.study.ui.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member login(MemberLoginDto memberLoginDto) {
        return memberRepository.findByLoginId(memberLoginDto.getLoginId())
                .filter(member -> passwordEncoder.isMatch(memberLoginDto.getPassword(), member.getPassword()))
                .orElseThrow(InvalidCredentialsException::new);
    }
}
