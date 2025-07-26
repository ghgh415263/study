package com.example.study.learningtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
public class NamedLockReleaseTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testNamedLockReleaseByDifferentSessions() throws Exception {

        try (Connection conn1 = dataSource.getConnection();
             Connection conn2 = dataSource.getConnection();
             Statement stmt1 = conn1.createStatement();
             Statement stmt2 = conn2.createStatement()) {

            // 세션1에서 락 획득
            ResultSet rs1 = stmt1.executeQuery("SELECT GET_LOCK('my_lock', 5)");
            assertTrue(rs1.next());
            assertEquals(1, rs1.getInt(1), "세션1이 락을 획득해야 한다.");

            // 세션2에서 락 해제 시도 (실패 예상)
            ResultSet rs2 = stmt2.executeQuery("SELECT RELEASE_LOCK('my_lock')");
            assertTrue(rs2.next());
            assertEquals(0, rs2.getInt(1), "세션2는 락을 해제할 수 없다.");

            // 락 정리
            stmt1.execute("SELECT RELEASE_LOCK('my_lock')");
        }
    }

    @Test
    void testAcquireSameLockTwiceSameSession() throws Exception {

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            // 첫 번째 락 획득
            ResultSet rs1 = stmt.executeQuery("SELECT GET_LOCK('my_lock', 5)");
            assertTrue(rs1.next());
            assertEquals(1, rs1.getInt(1), "첫 번째 락 획득 성공");

            // 같은 세션에서 같은 락을 다시 획득 시도 (재진입)
            ResultSet rs2 = stmt.executeQuery("SELECT GET_LOCK('my_lock', 5)");
            assertTrue(rs2.next());
            assertEquals(1, rs2.getInt(1), "두 번째 락 획득도 성공 (재진입)");

            // 락 정리
            stmt.execute("SELECT RELEASE_LOCK('my_lock')");
        }
    }
}
