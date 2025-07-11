package org.scoula.config;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
class RootConfigTest {

    // 필드 위에 주입!
    @Autowired
    private DataSource dataSource; // hikariDBCP 들어감.

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    @DisplayName("dbcp 연결 정보 확인")
    void dataSource() throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            log.info("dbcp로부터 연결 하나 가지고 왔음.");
            log.info(con);
        }
    }

    @Test
    public void testSqlSessionFactory() {
        try (SqlSession session = sqlSessionFactory.openSession();
             Connection conn = session.getConnection()) {
            log.info(session);
            log.info(conn);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}