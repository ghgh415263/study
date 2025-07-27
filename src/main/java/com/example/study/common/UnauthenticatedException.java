package com.example.study.common;

import org.springframework.http.HttpStatus;

public class UnauthenticatedException extends CustomException {
    public UnauthenticatedException() {
        super("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
    }
}
