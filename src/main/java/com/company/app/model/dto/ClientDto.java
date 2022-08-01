package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<RecipeDto> recipes;
    private List<OrderDto> orders;
    private boolean deleted;
}
