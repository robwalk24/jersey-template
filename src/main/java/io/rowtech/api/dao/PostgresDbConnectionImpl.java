package io.rowtech.api.dao;

import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDbConnectionImpl implements DbConnection {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(PostgresDbConnectionImpl.class);
    private Connection connection;

    @Inject
    public PostgresDbConnectionImpl(){}

    @Override
    public Connection get() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/hero",
                                "hero", "password");
            }
        } catch (ClassNotFoundException | SQLException e) {
            String message = String.format("Failed to get database connection. %s", e.getMessage());
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }

        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection == null) return;

        connection.close();
        connection = null;
    }
}
