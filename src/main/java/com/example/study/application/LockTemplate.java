package com.example.study.application;

import com.example.study.domain.LockManager;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class LockTemplate {

    private final LockManager lockManager;
    private final int timeoutSeconds;

    public LockTemplate(LockManager lockManager) {
        this.lockManager = lockManager;
        this.timeoutSeconds = 3;
    }

    public <T> T executeWithLock(String lockName, Supplier<T> supplier) {
        if (lockName == null || lockName.isBlank()) {
            throw new IllegalArgumentException("lockName은 필수값입니다.");
        }

        boolean locked = lockManager.acquireLock(lockName, timeoutSeconds);
        if (!locked) {
            throw new RuntimeException("락 획득 실패: " + lockName);
        }

        try {
            return supplier.get();
        } finally {
            lockManager.releaseLock(lockName);
        }
    }
}
