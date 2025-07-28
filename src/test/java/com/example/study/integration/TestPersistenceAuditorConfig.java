package com.example.study.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@TestConfiguration
public class TestPersistenceAuditorConfig {

    /**
     * 테스트용 AuditorAware 빈 등록
     * 항상 "testUser" 라는 감사자명을 반환합니다.
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("testUser");
    }
}
