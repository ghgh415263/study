package com.example.study.webcommon;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 애플리케이션 전역에서 사용할 커스텀 런타임 예외의 기본 추상 클래스입니다.
 *
 * 이 클래스를 상속받은 구체적인 예외 클래스들은
 * 예외 메시지와 함께 적절한 HTTP 상태 코드를 포함할 수 있습니다.
 *
 * {@link GlobalExceptionHandler} 에서 이 정보를 활용해
 * 클라이언트에 명확한 에러 응답을 반환할 수 있도록 설계되었습니다.
 */
@Getter
public abstract class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    protected CustomException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
