package com.example.study.member.command.domain;

import com.example.study.common.CustomException;
import org.springframework.http.HttpStatus;

/**
 * 리소스의 현재 상태로는 요청한 작업을 수행할 수 없을 때 발생하는 예외입니다.
 */
public class InvalidMemberStateException extends CustomException {

    /**
     * @param currentState 현재 리소스 상태
     * @param targetState  변경하려는 목표 상태
     */
    public InvalidMemberStateException(Enum<?> currentState, Enum<?> targetState) {
        super(String.format("현재 상태(%s)로는 %s 상태로 변경할 수 없습니다.", currentState, targetState), HttpStatus.BAD_REQUEST);
    }
}
