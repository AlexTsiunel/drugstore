package com.company.app.service.impl;

import com.company.app.dao.DoctorDao;
import com.company.app.model.converter.DoctorConverter;
import com.company.app.model.dto.DoctorDto;
import com.company.app.model.entity.Doctor;
import com.company.app.service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDao doctorDao;
    private DoctorConverter doctorConverter;

    public DoctorServiceImpl(DoctorDao doctorDao, DoctorConverter doctorConverter) {
        this.doctorDao = doctorDao;
        this.doctorConverter = doctorConverter;
    }

    @Override
    public DoctorDto getById(Long id) {
        return doctorConverter.convertEntityToDto(doctorDao.getById(id));
    }

    @Override
    public DoctorDto saveOrUpdate(DoctorDto doctorDto) {
        Doctor doctor = doctorDao.saveOrUpdate(doctorConverter.convertDtoToEntity(doctorDto));
        return doctorConverter.convertEntityToDto(doctor);
    }

    @Override
    public List<DoctorDto> getAll() {
        return doctorConverter.convertEntitiesToDtos(doctorDao.getAll());
    }

    @Override
    public void delete(Long id) {
        doctorDao.delete(id);
    }
}
