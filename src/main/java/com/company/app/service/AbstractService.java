package com.company.app.service;

import com.company.app.model.api.Dto;

import java.util.List;

public interface AbstractService <K, T extends Dto>{
    T getById(K id);

    T saveOrUpdate(T dto);

    List<T> getAll();

    void delete(K id);
}
