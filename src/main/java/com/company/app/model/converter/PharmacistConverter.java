package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.PharmacistDto;
import com.company.app.model.entity.Pharmacist;

public class PharmacistConverter extends Convert<PharmacistDto, Pharmacist> {

    @Override
    public PharmacistDto convertEntityToDto(Pharmacist entity) {
        PharmacistDto pharmacistDto = new PharmacistDto();
        if(pharmacistDto != null){
            pharmacistDto.setId(entity.getId());
            pharmacistDto.setFirstName(entity.getFirstName());
            pharmacistDto.setLastName(entity.getLastName());
            pharmacistDto.setEmail(entity.getEmail());
            pharmacistDto.setPassword(entity.getPassword());
            pharmacistDto.setDeleted(entity.isDeleted());
        }
        return pharmacistDto;
    }

    @Override
    public Pharmacist convertDtoToEntity(PharmacistDto dto) {
        Pharmacist pharmacist = new Pharmacist();
        if(pharmacist != null){
            pharmacist.setId(dto.getId());
            pharmacist.setFirstName(dto.getFirstName());
            pharmacist.setLastName(dto.getLastName());
            pharmacist.setEmail(dto.getEmail());
            pharmacist.setPassword(dto.getPassword());
            pharmacist.setDeleted(dto.isDeleted());
        }
        return pharmacist;
    }
}
