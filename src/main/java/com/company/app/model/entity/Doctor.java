package com.company.app.model.entity;

import lombok.Data;

import java.util.List;
@Data
public class Doctor extends PersistableEntityImpl {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Recipe> recipes;
    private boolean deleted;
}
