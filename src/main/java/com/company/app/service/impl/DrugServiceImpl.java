package com.company.app.service.impl;

import com.company.app.dao.DrugDao;
import com.company.app.model.converter.DrugConverter;
import com.company.app.model.entity.Drug;
import com.company.app.service.DrugService;
import com.company.app.model.dto.DrugDto;

import java.util.List;

public class DrugServiceImpl implements DrugService {
    private DrugDao drugDao;
    private DrugConverter drugConverter;

    public DrugServiceImpl(DrugDao drugDao, DrugConverter drugConverter) {
        this.drugDao = drugDao;
        this.drugConverter = drugConverter;
    }

    @Override
    public DrugDto getById(Long id) {
        return drugConverter.convertEntityToDto(drugDao.getById(id));

    }

    @Override
    public DrugDto saveOrUpdate(DrugDto drugDto) {
        Drug drug = drugDao.saveOrUpdate(drugConverter.convertDtoToEntity(drugDto));
        return drugConverter.convertEntityToDto(drug);
    }

    @Override
    public List<DrugDto> getAll() {

        return drugConverter.convertEntitiesToDtos(drugDao.getAll());
    }

    @Override
    public void delete(Long id) {
        drugDao.delete(id);
    }
}
