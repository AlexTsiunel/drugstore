package com.company.app.dao;

import com.company.app.model.api.PersistableEntity;

import java.sql.SQLException;
import java.util.List;

public interface AbsrtactDao<K, T extends PersistableEntity> {
    T getById(K id);

    T saveOrUpdate(T entity);

    List<T> getAll();

    boolean delete(K id) throws SQLException;
}
