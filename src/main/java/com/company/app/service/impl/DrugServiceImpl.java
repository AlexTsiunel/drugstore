package com.company.app.service.impl;

import com.company.app.dao.DrugDao;
import com.company.app.model.converter.DrugConverter;
import com.company.app.model.entity.Drug;
import com.company.app.model.exception.NoDeleteElementException;
import com.company.app.model.exception.NoSuchElementException;
import com.company.app.service.DrugService;
import com.company.app.model.dto.DrugDto;
import lombok.extern.log4j.Log4j2;

import java.util.List;
@Log4j2
public class DrugServiceImpl implements DrugService {
    private DrugDao drugDao;
    private DrugConverter drugConverter;

    public DrugServiceImpl(DrugDao drugDao, DrugConverter drugConverter) {
        this.drugDao = drugDao;
        this.drugConverter = drugConverter;
    }

    @Override
    public DrugDto getById(Long id) {
        log.debug("Calling the 'getById' method");
        Drug drug = drugDao.getById(id);
        if(drug == null){
            throw new NoSuchElementException(String.format("Drug with id=%d not found",id));
        }
        return drugConverter.convertEntityToDto(drug);
    }

    @Override
    public DrugDto saveOrUpdate(DrugDto drugDto) {
        log.debug("Calling the 'saveOrUpdate' method");
        Drug drug = drugDao.saveOrUpdate(drugConverter.convertDtoToEntity(drugDto));
        return drugConverter.convertEntityToDto(drug);
    }

    @Override
    public List<DrugDto> getAll() {
        return drugConverter.convertEntitiesToDtos(drugDao.getAll());
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling the 'delete' method");
        if(!drugDao.delete(id)){
            throw new NoDeleteElementException("Failed to delete drug with id=" + id);
        }
    }
}
