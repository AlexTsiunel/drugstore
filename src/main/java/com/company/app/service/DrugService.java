package com.company.app.service;

import com.company.app.model.dto.DrugDto;

import java.util.List;
import java.util.Map;

public interface DrugService extends AbstractService<Long, DrugDto> {
public Map<DrugDto, Integer> convertCartToDrugsMap(Map<Long, Integer> cart);
    List<DrugDto> getAll(int limit, long offset);
    long count();
}
