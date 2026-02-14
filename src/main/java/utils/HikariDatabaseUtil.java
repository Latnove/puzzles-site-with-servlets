package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDatabaseUtil {
    private static HikariDataSource dataSource;

    public static HikariDataSource create(String url, String username, String password) {
        if (dataSource != null) {
            return dataSource;
        }

        try {
            Class.forName("org.postgresql.Driver");
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(5);
            config.setConnectionTimeout(30000);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);

            dataSource = new HikariDataSource(config);

            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
