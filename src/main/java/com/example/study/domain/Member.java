package com.example.study.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Embedded
    private AddressVO memberAddress;

    public Member(String loginId, String password, String email, String name, AddressVO memberAddress) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.memberAddress = memberAddress;
    }
}
