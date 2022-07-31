package com.company.app.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbsrtactDao<K, T> {
    T getById(K id);

    T saveOrUpdate(T entity);

    List<T> getAll();

    boolean delete(K id) throws SQLException;
}
