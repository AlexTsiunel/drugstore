package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import com.company.app.model.entity.Client;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class RecipeDto implements Dto {
    private Long id;
    private ClientDto client;
    private DoctorDto doctor;
    private Date startDate;
    private Date endDate;
    private boolean deleted;
}
