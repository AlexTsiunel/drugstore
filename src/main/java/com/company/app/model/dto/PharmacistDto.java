package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import lombok.Data;

import java.util.List;

@Data
public class PharmacistDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean deleted;
}
