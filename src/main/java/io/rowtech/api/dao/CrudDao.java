package io.rowtech.api.dao;

import io.rowtech.api.domain.Entity;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T extends Entity> extends AutoCloseable {

    Optional<T> get(int id);

    List<T> getAll();

    T create(T entity);

    boolean update(T entity);

    void delete(int id);
}
