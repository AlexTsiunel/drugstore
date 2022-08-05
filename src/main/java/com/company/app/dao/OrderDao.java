package com.company.app.dao;

import com.company.app.model.entity.Order;

import java.util.List;

public interface OrderDao extends AbstractDao<Long, Order> {
    List<Order> getAllByClientId(long id);

    List<Order> getAllByPharmacistId(long id);
}
