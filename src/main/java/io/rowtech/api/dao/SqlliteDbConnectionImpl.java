package io.rowtech.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlliteDbConnectionImpl implements DbConnection {
    private Connection connection;

    @Override
    public Connection get() {
        try {
            if (connection == null) {
                Class<?> aClass = Class.forName("org.sqlite.JDBC");
                connection = DriverManager
                        .getConnection(String.format("jdbc:sqlite:/usr/local/share/hero.db"));


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
