package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManger() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}", connection, connection.getClass());
        log.info("connection={}, class={}", connection1, connection1.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);

        useDataSource(dataSource);
    }

    private static void useDataSource(DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        Connection connection1 = dataSource.getConnection();

        log.info("connection={}, class={}", connection, connection.getClass());
        log.info("connection={}, class={}", connection1, connection1.getClass());
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setPoolName("myPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }



}
