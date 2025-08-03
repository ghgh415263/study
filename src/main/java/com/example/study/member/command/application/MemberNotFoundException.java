package com.example.study.member.command.application;

import com.example.study.common.CustomException;
import org.springframework.http.HttpStatus;

/**
 * 주어진 ID에 해당하는 회원을 찾을 수 없을 때 발생하는 예외입니다.
 */
public class MemberNotFoundException extends CustomException {

    public MemberNotFoundException() {
        super("해당 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
    }
}
