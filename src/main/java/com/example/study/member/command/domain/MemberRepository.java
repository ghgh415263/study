package com.example.study.member.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID>, RevisionRepository<Member, UUID, Integer> {

    Optional<Member> findByLoginId(String loginId);
}
