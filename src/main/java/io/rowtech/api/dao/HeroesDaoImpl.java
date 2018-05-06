package io.rowtech.api.dao;

import io.rowtech.api.domain.Hero;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeroesDaoImpl implements CrudDao<Hero> {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(HeroesDaoImpl.class);
    private final DbConnection dbConnection;

    @Inject
    public HeroesDaoImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void close() throws Exception {
        dbConnection.close();
    }

    @Override
    public Optional<Hero> get(int id) {
        try {
            BeanHandler<Hero> beanHandler = new BeanHandler<>(Hero.class);
            Connection connection = dbConnection.get();
            QueryRunner runner = new QueryRunner();
            return Optional.ofNullable(runner.query(connection, Sql.GET, beanHandler, id));
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public List<Hero> getAll() {
        try {
            BeanListHandler<Hero> beanListHandler = new BeanListHandler<>(Hero.class);
            QueryRunner runner = new QueryRunner();
            Connection connection = dbConnection.get();
            return runner.query(connection, Sql.GET_ALL, beanListHandler);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public Hero create(Hero entity) {
        try {
            BeanHandler<Hero> beanHandler = new BeanHandler<>(Hero.class);
            QueryRunner runner = new QueryRunner();
            Connection connection = dbConnection.get();
            return runner.insert(connection, Sql.CREATE, beanHandler, entity.getName());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Hero entity) {
        try {
            QueryRunner runner = new QueryRunner();
            Connection connection = dbConnection.get();
            return runner.update(connection, Sql.UPDATE,
                    entity.getName(), entity.getId()) > 0;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            QueryRunner runner = new QueryRunner();
            Connection connection = dbConnection.get();
            runner.execute(connection, Sql.DELETE, id);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static class Sql {
        public static final String CREATE = "insert into hero (name) values (?)";
        public static final String GET = "select * from hero where id = ?";
        public static final String GET_ALL = "select * from hero";
        public static final String UPDATE = "update hero set name = ? where id = ?";
        public static final String DELETE = "delete from hero where id = ?";
    }
}