package com.example.study.domain;

/**
 * 비밀번호 인코딩 및 검증을 위한 전략 인터페이스입니다.
 *
 * 이 인터페이스는 평문 비밀번호를 해시하거나,
 * 저장된 해시값과 비교하는 기능을 추상화합니다.
 * 구현체를 통해 다양한 해시 알고리즘(Bcrypt, Argon2 등)을 적용할 수 있으며,
 * 테스트 환경에서는 단순한 구현체로 대체 가능합니다.
 */
public interface PasswordEncoder {

    /**
     * 평문 비밀번호를 해시합니다.
     *
     * @param raw 해시할 평문 비밀번호
     * @return 해시된 문자열
     */
    String hashPassword(String raw);

    /**
     * 평문 비밀번호가 해시된 비밀번호와 일치하는지 검증합니다.
     *
     * @param raw    입력된 평문 비밀번호
     * @param hashed 저장된 해시 비밀번호
     * @return 일치 여부
     */
    boolean isMatch(String raw, String hashed);
}