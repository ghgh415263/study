package com.example.study.member.command.domain;

import com.example.study.order.command.domain.AddressVO;
import com.example.study.common.persistance.BaseUpdateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Audited
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
