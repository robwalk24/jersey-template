package io.rowtech.api.dao;

import java.sql.Connection;

public interface DbConnection extends AutoCloseable {
    Connection get();
}
