package com.example.study.common;

/**
 * 락 획득 실패 시 발생하는 커스텀 런타임 예외 클래스
 */
public class LockAcquisitionFailureException extends RuntimeException {

    public LockAcquisitionFailureException(String message) {
        super(message);
    }
}
