package com.company.app.service.dto;

import com.company.app.dao.entity.Client;
import com.company.app.dao.entity.Drug;
import com.company.app.dao.entity.PersistableEntityImpl;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class RecipeDto implements Dto {
    private Long id;
    private Client client;
    private DoctorDto doctor;
    private List<DrugDto> drugs;
    private Date startDate;
    private Date endDate;
    private boolean deleted;
}
