package com.example.study.application;

import com.example.study.webcommon.CustomException;
import org.springframework.http.HttpStatus;

/**
 * 로그인 ID가 중복될 경우 발생하는 사용자 정의 예외입니다.
 * 회원 가입 시 중복된 ID 사용을 방지하기 위해 사용됩니다.
 */
public class DuplicatedLoginIdException extends CustomException {

    private static final String DEFAULT_MESSAGE = "이미 사용 중인 로그인 ID입니다.";

    public DuplicatedLoginIdException() {
        super(DEFAULT_MESSAGE, HttpStatus.CONFLICT);  // 409 상태코드 사용 (중복 충돌)
    }

    public DuplicatedLoginIdException(String loginId) {
        super("이미 사용 중인 로그인 ID입니다: " + loginId, HttpStatus.CONFLICT);
    }
}