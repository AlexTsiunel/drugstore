package com.company.app.model.dto;

import com.company.app.model.api.Dto;
import com.company.app.model.entity.Client;
import lombok.Data;

import java.util.Map;

@Data
public class OrderDto implements Dto {
    private Long id;
    private ClientDto client;
    private Map<DrugDto, Integer> drugs;
    private PharmacistDto pharmacist;
    private OrderStatus status;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        AWAITING_PAYMENT,
        PAID,
        CANCELED
    }
}
