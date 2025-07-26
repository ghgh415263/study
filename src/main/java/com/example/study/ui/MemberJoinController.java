package com.example.study.ui;

import com.example.study.application.MemberJoinDto;
import com.example.study.application.MemberJoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class MemberJoinController {

    private final MemberJoinService memberJoinService;

    @PostMapping
    public ResponseEntity<Void> join(@Validated @RequestBody MemberJoinDto dto) {
        String memberId = memberJoinService.saveMember(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()            // /api/members
                .path("/{id}")                   // /{id} 추가
                .buildAndExpand(memberId)        // memberId 값 넣음
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
