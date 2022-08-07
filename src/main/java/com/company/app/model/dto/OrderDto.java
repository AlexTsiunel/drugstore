package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import com.company.app.model.entity.Client;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class OrderDto implements Dto {
    private Long id;
    private ClientDto client;
    private PharmacistDto pharmacist;
    private Map<DrugDto, Integer> drugs;
    private BigDecimal totalCoast;
    private OrderStatus status;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        PENDING,
        PAID,
        CANCELED
    }
}
