package com.example.study.application.member;

import com.example.study.webcommon.CustomException;
import com.example.study.webcommon.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;

/**
 * 로그인 시 잘못된 이메일 또는 비밀번호로 인해 인증에 실패했을 때 발생하는 예외입니다.
 *
 * 이 예외는 {@link GlobalExceptionHandler} 에서 처리되어
 * 클라이언트에게 401 Unauthorized 상태와 함께 메시지를 전달합니다.
 */
public class InvalidCredentialsException extends CustomException {

    public InvalidCredentialsException() {
        super("아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
    }
}
