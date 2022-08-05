package com.company.app.model.entity;

import lombok.Data;


@Data
public class Client extends PersistableEntityImpl {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean deleted;
}
