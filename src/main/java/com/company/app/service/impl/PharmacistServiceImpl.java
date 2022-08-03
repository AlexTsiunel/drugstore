package com.company.app.service.impl;

import com.company.app.dao.PharmacistDao;
import com.company.app.model.converter.PharmacistConverter;
import com.company.app.model.dto.PharmacistDto;
import com.company.app.model.entity.Pharmacist;
import com.company.app.service.PharmacistService;

import java.util.List;

public class PharmacistServiceImpl implements PharmacistService {
    private PharmacistDao pharmacistDao;
    private PharmacistConverter pharmacistConverter;

    public PharmacistServiceImpl(PharmacistDao pharmacistDao, PharmacistConverter pharmacistConverter) {
        this.pharmacistDao = pharmacistDao;
        this.pharmacistConverter = pharmacistConverter;
    }


    @Override
    public PharmacistDto getById(Long id) {
        return pharmacistConverter.convertEntityToDto(pharmacistDao.getById(id));
    }

    @Override
    public PharmacistDto saveOrUpdate(PharmacistDto pharmacistDto) {
        Pharmacist pharmacist = pharmacistDao.saveOrUpdate(pharmacistConverter.convertDtoToEntity(pharmacistDto));
        return pharmacistConverter.convertEntityToDto(pharmacist);
    }

    @Override
    public List<PharmacistDto> getAll() {
        return pharmacistConverter.convertEntitiesToDtos(pharmacistDao.getAll());
    }

    @Override
    public void delete(Long id) {
        pharmacistDao.delete(id);
    }
}
