package com.example.study.learningtest;

import com.example.study.integration.TestPersistenceAuditorConfig;
import com.example.study.order.command.domain.AddressVO;
import com.example.study.order.command.domain.DeliveryAddress;
import com.example.study.order.command.domain.DeliveryAddressRepository;
import com.example.study.order.command.infra.JpaDeliveryAddressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestPersistenceAuditorConfig.class)
@DataJpaTest
public class DeliveryAddressTest {

    @Autowired
    private EntityManager em;

    private DeliveryAddressRepository deliveryAddressRepository;

    @BeforeEach
    void setUp() {
        deliveryAddressRepository = new JpaDeliveryAddressRepository(em);
    }

    @Test
    @DisplayName("DeliveryAddress를 수정한다")
    void updateNewDeliveryAddress() {

        // given
        UUID memberId = UUID.randomUUID();
        AddressVO address = new AddressVO("06000", "서울 강남구", "101호");
        DeliveryAddress deliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", address);

        DeliveryAddress saved = deliveryAddressRepository.save(deliveryAddress);

        // 영속성 컨텍스트 초기화 (flush + clear)
        em.flush();
        em.clear();

        // given
        AddressVO changedAdress = new AddressVO("38750", "춘천시 명동", "702호");
        DeliveryAddress changedAdressDeliveryAddress = new DeliveryAddress(memberId.toString(), "상훈네집", changedAdress);

        // when
        DeliveryAddress changedEntity = deliveryAddressRepository.findById(saved.getId()).orElseThrow(() -> new EntityNotFoundException("배송주소를 찾을 수 없습니다."));
        changedEntity.updateDeliveryAddress(changedAdressDeliveryAddress.getName(), changedAdress);

        // 영속성 컨텍스트 초기화 (flush + clear)
        em.flush();
        em.clear();

        // then
        DeliveryAddress foundEntity = deliveryAddressRepository.findById(saved.getId()).orElseThrow(() -> new EntityNotFoundException("배송주소를 찾을 수 없습니다."));

        assertThat(foundEntity.getName()).isEqualTo("상훈네집");
        assertThat(foundEntity.getAddress().getZipCode()).isEqualTo("38750");
        assertThat(foundEntity.getAddress().getBaseAddress()).isEqualTo("춘천시 명동");
        assertThat(foundEntity.getAddress().getDetailAddress()).isEqualTo("702호");
    }
}