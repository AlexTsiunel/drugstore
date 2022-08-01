package com.company.app.service.dto;

import com.company.app.dao.entity.Order;
import com.company.app.dao.entity.PersistableEntityImpl;
import com.company.app.dao.entity.Recipe;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    List<RecipeDto> recipes;
    List<OrderDto> orders;
    private boolean deleted;
}
