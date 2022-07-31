package com.company.app.dao.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class Recipe extends PersistableEntityImpl {
    private Client client;
    private Doctor doctor;
    private List<Drug> drug;
    private Date startDate;
    private Date endDate;
    private boolean deleted;
}
