package com.example.study.infra;

import com.example.study.domain.LockManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * MySQL 네임드 락을 사용하는 구현체입니다.
 */
@Component
public class MysqlNamedLockManager implements LockManager {

    private final JdbcTemplate jdbcTemplate;

    public MysqlNamedLockManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean acquireLock(String lockName, int timeoutSeconds) {
        String sql = "SELECT GET_LOCK(?, ?)";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, lockName, timeoutSeconds);
        return result != null && result == 1;
    }

    @Override
    public boolean releaseLock(String lockName) {
        String sql = "SELECT RELEASE_LOCK(?)";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, lockName);
        return result != null && result == 1;
    }
}
