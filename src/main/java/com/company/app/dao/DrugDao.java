package com.company.app.dao;

import com.company.app.model.entity.Drug;

import java.util.List;

public interface DrugDao extends AbstractDao<Long, Drug> {
    List<Drug> getAll(int limit, long offset);
    long count();
}
