package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.DoctorDto;
import com.company.app.model.entity.Doctor;

public class DoctorConverter extends Convert<DoctorDto, Doctor> {

    @Override
    public DoctorDto convertEntityToDto(Doctor entity) {
        DoctorDto doctorDto = new DoctorDto();
        if(doctorDto != null){
            doctorDto.setId(entity.getId());
            doctorDto.setFirstName(entity.getFirstName());
            doctorDto.setLastName(entity.getLastName());
            doctorDto.setEmail(entity.getEmail());
            doctorDto.setPassword(entity.getPassword());
            doctorDto.setDeleted(entity.isDeleted());
        }
        return doctorDto;
    }

    @Override
    public Doctor convertDtoToEntity(DoctorDto dto) {
        Doctor doctor = new Doctor();
        if(doctor != null){
            doctor.setId(dto.getId());
            doctor.setFirstName(dto.getFirstName());
            doctor.setLastName(dto.getLastName());
            doctor.setEmail(dto.getEmail());
            doctor.setPassword(dto.getPassword());
            doctor.setDeleted(dto.isDeleted());
        }
        return doctor;
    }
}
