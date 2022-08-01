package com.company.app.service.dto;

import lombok.Data;

import java.util.List;
@Data
public class DoctorDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    List<RecipeDto> recipes;
    private boolean deleted;
}
