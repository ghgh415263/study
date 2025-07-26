package com.example.study.application.member;

import com.example.study.webcommon.CustomException;
import org.springframework.http.HttpStatus;

/**
 * 비밀번호가 약한 경우 발생하는 런타임 예외 클래스입니다.
 *
 * 기본 메시지는 "숫자와 특수문자를 각각 하나 이상 포함해주세요."이며,
 * 필요에 따라 사용자 정의 메시지로 예외를 생성할 수 있습니다.
 *
 * 이 예외는 비밀번호 검증 로직에서 강도 조건을 만족하지 않을 때 던져집니다.
 */
public class WeakPasswordException extends CustomException {

    private static final String DEFAULT_MESSAGE = "숫자와 특수문자를 각각 하나 이상 포함해주세요.";

    public WeakPasswordException() {
        super(DEFAULT_MESSAGE, HttpStatus.BAD_REQUEST);  // 400 상태코드 사용 (잘못된 요청)
    }

    public WeakPasswordException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}