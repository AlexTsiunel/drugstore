package com.company.app.service.dto;

import com.company.app.dao.entity.PersistableEntityImpl;
import lombok.Data;

import java.util.List;

@Data
public class PharmacistDto implements Dto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    List<OrderDto> orders;
    private boolean deleted;
}
