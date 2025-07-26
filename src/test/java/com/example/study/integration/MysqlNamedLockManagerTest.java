package com.example.study.integration;

import com.example.study.infra.MysqlNamedLockManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
class MysqlNamedLockManagerTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private MysqlNamedLockManager lockManager;

	@BeforeEach
	void setUp() {
		this.lockManager = new MysqlNamedLockManager(jdbcTemplate);
	}

	@Test
	void testAcquireAndReleaseLock() {
		String lockName = "test_lock";

		assertTrue(lockManager.acquireLock(lockName, 1));
		assertTrue(lockManager.releaseLock(lockName));
	}

	@Test
	void testAcquireLock_whenAlreadyLocked_thenFail() throws Exception {
		String lockName = "conflict_lock";
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<Boolean> result1 = executor.submit(() -> lockManager.acquireLock(lockName, 1));
		Future<Boolean> result2 = executor.submit(() -> lockManager.acquireLock(lockName, 1));

		try {
			boolean firstLock = result1.get(4, TimeUnit.SECONDS);
			boolean secondLock = result2.get(4, TimeUnit.SECONDS);

			assertTrue(firstLock, "첫 번째 락 획득에 실패했습니다");
			assertFalse(secondLock, "두 번째 락은 실패해야 합니다 (이미 점유 중)");
		} finally {
			lockManager.releaseLock(lockName);
			executor.shutdownNow();
		}
	}
}
