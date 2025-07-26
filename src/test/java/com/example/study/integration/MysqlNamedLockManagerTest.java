package com.example.study.integration;

import com.example.study.infra.MysqlNamedLockManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 같은 세션에서만 락 해제가 가능하기 때문에,
 * @Transactional을 사용해 같은 트랜잭션(즉, 같은 세션) 안에서 테스트가 실행되도록 했습니다.
 */
@JdbcTest
@Transactional
class MysqlNamedLockManagerTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private MysqlNamedLockManager lockManager;

	@BeforeEach
	void setUp() {
		this.lockManager = new MysqlNamedLockManager(jdbcTemplate);
	}

	@Test
	void testReleaseLock() {
		String lockName = "test_lock";

		assertTrue(lockManager.acquireLock(lockName, 1));
		assertTrue(lockManager.releaseLock(lockName));
	}

	/**
	 * 같은 트랜잭션 범위 내에서 다른 JdbcTemplate을 생성해
	 * 실제로 다른 세션에서 락 획득 시도가 어떻게 동작하는지 검증합니다.
	 */
	@Test
	void testAcquireLock_whenAlreadyLocked_thenFail() throws Exception {
		String lockName = "conflict_lock";

		boolean firstLock = lockManager.acquireLock(lockName, 1);

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			JdbcTemplate otherJdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(conn, true));
			MysqlNamedLockManager otherLockManager = new MysqlNamedLockManager(otherJdbcTemplate);

			boolean secondLock = otherLockManager.acquireLock(lockName, 1);

			assertTrue(firstLock, "첫 번째 락 획득에 실패했습니다");
			assertFalse(secondLock, "두 번째 락은 실패해야 합니다 (이미 점유 중)");
		} finally {
			lockManager.releaseLock(lockName);
		}
	}
}
