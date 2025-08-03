package com.example.study.learningtest;

import com.example.study.integration.TestPersistenceAuditorConfig;
import com.example.study.member.command.domain.Member;
import com.example.study.member.command.domain.MemberRepository;
import com.example.study.order.command.domain.AddressVO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(TestPersistenceAuditorConfig.class)
public class MemberAudRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setup() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    @AfterEach
    void cleanup() {
        // 후처리: 별도 트랜잭션 내에서 관련 테이블 데이터 삭제 (테스트 독립성 확보)
        transactionTemplate.execute(status -> {
            em.createNativeQuery("DELETE FROM member").executeUpdate();
            em.createNativeQuery("DELETE FROM member_aud").executeUpdate();
            em.createNativeQuery("DELETE FROM revinfo").executeUpdate();
            return null;
        });
    }

    @Test
    @DisplayName("히스토리용 aud가 잘저장되는지 확인")
    void memberSave_thenRevisionShouldBeStored() {

        // given: 테스트용 AddressVO와 Member 객체 생성
        AddressVO address = new AddressVO("1234551", "Seoul", "Gangnam");
        Member member = new Member("login123451", "pw1234", "test@example.com", "홍길동", address);

        // when: 별도 트랜잭션(REQUIRES_NEW)으로 member 저장
        transactionTemplate.execute(status -> {
            memberRepository.save(member); // DB에 회원 저장 및 리비전 자동 생성
            return null;
        });

        // then: 저장된 회원의 리비전 정보 조회
        var revisions = memberRepository.findRevisions(member.getId());

        //저장된 리비전 개수 확인 (저장한 변경 이력 개수)
        var count = revisions.stream().count();

        // 최신 리비전 시점의 Member 엔티티 조회
        Member latestMember = revisions.getLatestRevision().getEntity();

        // 최신 Member 엔티티의 ID가 저장한 member와 동일한지 검증, 리비전 개수가 1개임을 검증 (초기 저장 1회)
        assertThat(latestMember.getId()).isEqualTo(member.getId());
        assertThat(count).isEqualTo(1);
    }
}
