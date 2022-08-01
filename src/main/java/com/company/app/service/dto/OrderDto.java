package com.company.app.service.dto;

import com.company.app.dao.entity.Client;
import com.company.app.dao.entity.Drug;
import lombok.Data;

import java.util.Map;

@Data
public class OrderDto implements Dto {
    private Long id;
    private Client client;
    private Map<DrugDto, Integer> drugs;
    private PharmacistDto pharmacist;
    OrderStatus status;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        AWAITING_PAYMENT,
        PAID,
        CANCELED
    }
}
