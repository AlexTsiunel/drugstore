package com.company.app.dao;

import com.company.app.model.api.PersistableEntity;

import java.util.List;

public interface AbstractDao<K, T extends PersistableEntity> {
    T getById(K id);

    T saveOrUpdate(T entity);

    List<T> getAll();

    boolean delete(K id);
}
