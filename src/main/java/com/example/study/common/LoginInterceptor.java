package com.example.study.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    private static final String LOGIN_SESSION_KEY = "loginId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(LOGIN_SESSION_KEY) == null) {
            throw new UnauthenticatedException();
        }

        // 로그인된 경우 → 요청 계속 진행
        return true;
    }
}
