package com.example.study.domain.member;

import org.springframework.stereotype.Component;

/**
 * 비밀번호 강도 측정을 위한 유틸리티 클래스입니다.
 *
 * 현재 구현에서는 다음 두 가지 조건을 만족하지 않으면
 * 비밀번호를 '약함(Weak)'으로 간주합니다:
 *  - 하나 이상의 숫자 포함
 *  - 하나 이상의 특수 문자 포함
 *
 * 특수 문자는 다음 문자들로 정의됩니다:
 * !@#$%&*()'+,-./:;<=>?[]^_`{|}
 *
 */
@Component
public class PasswordMeter {

    /**
     * 비밀번호에서 허용하는 특수 문자 목록.
     */
    private String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";

    /**
     * 비밀번호가 약한지 여부를 검사합니다.
     *
     * @param password 검사할 비밀번호
     * @return 비밀번호가 약하면 true, 그렇지 않으면 false
     */
    public Boolean isWeak(String password) {
        if (!hasNumber(password))
            return true;
        if (!hasSpecialCharacter(password))
            return true;
        return false;
    }

    /**
     * 비밀번호에 숫자가 포함되어 있는지 확인합니다.
     *
     * @param password 검사할 비밀번호
     * @return 숫자가 하나라도 포함되어 있으면 true, 아니면 false
     */
    private boolean hasNumber(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 비밀번호에 특수 문자가 포함되어 있는지 확인합니다.
     *
     * @param password 검사할 비밀번호
     * @return 특수 문자가 하나라도 포함되어 있으면 true, 아니면 false
     */
    private boolean hasSpecialCharacter(String password) {
        for (char c : password.toCharArray()) {
            if (specialCharactersString.contains(Character.toString(c))) {
                return true;
            }
        }
        return false;
    }
}
