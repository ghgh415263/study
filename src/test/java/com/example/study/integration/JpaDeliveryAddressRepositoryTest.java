package com.example.study.integration;

import com.example.study.order.command.domain.AddressVO;
import com.example.study.order.command.domain.DeliveryAddress;
import com.example.study.order.command.domain.DeliveryAddressRepository;
import com.example.study.order.command.infra.JpaDeliveryAddressRepository;
import jakarta.persistence.EntityManager;
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
public class JpaDeliveryAddressRepositoryTest {

    @Autowired
    private EntityManager em;

    private DeliveryAddressRepository deliveryAddressRepository;

    @BeforeEach
    void setUp() {
        deliveryAddressRepository = new JpaDeliveryAddressRepository(em);
    }

    @Test
    @DisplayName("DeliveryAddress 저장")
    void saveNewDeliveryAddress() {
        // given
        UUID memberId = UUID.randomUUID();
        AddressVO address = new AddressVO("06000", "서울 강남구", "101호");
        DeliveryAddress deliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", address);

        // when
        DeliveryAddress saved = deliveryAddressRepository.save(deliveryAddress);

        // 영속성 컨텍스트 초기화 (flush + clear)
        em.flush();
        em.clear();

        // then
        DeliveryAddress foundEntity = em.find(DeliveryAddress.class, saved.getId());

        assertThat(foundEntity.getId()).isEqualTo(saved.getId());
        assertThat(foundEntity.getMemberId()).isEqualTo(saved.getMemberId());
        assertThat(foundEntity.getName()).isEqualTo(saved.getName());
        assertThat(foundEntity.getAddress()).isEqualTo(saved.getAddress());
    }

    @Test
    @DisplayName("DeliveryAddress를 수정한다")
    void updateNewDeliveryAddress() {

        // given
        UUID memberId = UUID.randomUUID();
        AddressVO address = new AddressVO("06000", "서울 강남구", "101호");
        DeliveryAddress deliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", address);

        DeliveryAddress saved = deliveryAddressRepository.save(deliveryAddress);

        Long id = saved.getId();

        // 영속성 컨텍스트 초기화 (flush + clear)
        em.flush();
        em.clear();

        // given - 우편번호 변경
        AddressVO changedZipCode = new AddressVO("44444", "서울 강남구", "101호");
        DeliveryAddress changedZipCodeDeliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", changedZipCode);

        //when
        DeliveryAddress modified = deliveryAddressRepository.modify(saved.getId(), changedZipCodeDeliveryAddress);

        //then
        DeliveryAddress foundEntity = em.find(DeliveryAddress.class, modified.getId());

        assertThat(foundEntity.getName()).isEqualTo("우리집");
        assertThat(foundEntity.getAddress().getZipCode()).isEqualTo("44444");
        assertThat(foundEntity.getAddress().getBaseAddress()).isEqualTo("서울 강남구");
        assertThat(foundEntity.getAddress().getDetailAddress()).isEqualTo("101호");

        // given - 메인주소 변경
        AddressVO changedBaseAddress = new AddressVO("06000", "경주 팔곡동", "101호");
        DeliveryAddress changedBaseAddressDeliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", changedBaseAddress);

        //when
        DeliveryAddress modified2 = deliveryAddressRepository.modify(saved.getId(), changedBaseAddressDeliveryAddress);

        //then
        DeliveryAddress foundEntity2 = em.find(DeliveryAddress.class, modified2.getId());

        assertThat(foundEntity2.getName()).isEqualTo("우리집");
        assertThat(foundEntity2.getAddress().getZipCode()).isEqualTo("06000");
        assertThat(foundEntity2.getAddress().getBaseAddress()).isEqualTo("경주 팔곡동");
        assertThat(foundEntity2.getAddress().getDetailAddress()).isEqualTo("101호");

        // 서브주소 변경
        AddressVO changedDetailAddress = new AddressVO("06000", "서울 강남구", "570호");
        DeliveryAddress changedDetailAddressDeliveryAddress = new DeliveryAddress(memberId.toString(), "우리집", changedDetailAddress);

        //when
        DeliveryAddress modified3 = deliveryAddressRepository.modify(saved.getId(), changedDetailAddressDeliveryAddress);

        //then
        DeliveryAddress foundEntity3 = em.find(DeliveryAddress.class, modified3.getId());

        assertThat(foundEntity3.getName()).isEqualTo("우리집");
        assertThat(foundEntity3.getAddress().getZipCode()).isEqualTo("06000");
        assertThat(foundEntity3.getAddress().getBaseAddress()).isEqualTo("서울 강남구");
        assertThat(foundEntity3.getAddress().getDetailAddress()).isEqualTo("570호");

        // 집 이름 변경
        AddressVO changedName = new AddressVO("06000", "서울 강남구", "101호");
        DeliveryAddress changedNameDeliveryAddress = new DeliveryAddress(memberId.toString(), "김창호의집", changedName);

        //when
        DeliveryAddress modified4 = deliveryAddressRepository.modify(saved.getId(), changedNameDeliveryAddress);

        //then
        DeliveryAddress foundEntity4 = em.find(DeliveryAddress.class, modified4.getId());

        assertThat(foundEntity4.getName()).isEqualTo("김창호의집");
        assertThat(foundEntity4.getAddress().getZipCode()).isEqualTo("06000");
        assertThat(foundEntity4.getAddress().getBaseAddress()).isEqualTo("서울 강남구");
        assertThat(foundEntity4.getAddress().getDetailAddress()).isEqualTo("101호");

    }
}