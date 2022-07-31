package com.company.app.dao.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pharmacist extends PersistableEntityImpl {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    List<Order> orders;
    private boolean deleted;
}
