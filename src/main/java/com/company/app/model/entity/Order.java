package com.company.app.model.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Order extends PersistableEntityImpl {
    private Client client;
    private Map<Drug, Integer> drugs;
    private Pharmacist pharmacist;
    private OrderStatus status;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        AWAITING_PAYMENT,
        PAID,
        CANCELED
    }
}
