package com.example.study.order.command.domain;

import com.example.study.common.BaseUpdateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress extends BaseUpdateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(nullable = false)
    private String name;

    @Embedded
    private AddressVO address;

    public DeliveryAddress(String memberId, String name, AddressVO address) {
        this.memberId = UUID.fromString(memberId);
        this.name = name;
        this.address = address;
    }
}
