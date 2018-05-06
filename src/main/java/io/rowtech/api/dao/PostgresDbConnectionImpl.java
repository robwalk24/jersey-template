package io.rowtech.api.dao;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDbConnectionImpl implements DbConnection {
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
            e.printStackTrace();
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
