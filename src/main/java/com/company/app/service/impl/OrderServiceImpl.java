package com.company.app.service.impl;

import com.company.app.dao.OrderDao;
import com.company.app.model.converter.OrderConverter;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.entity.Order;
import com.company.app.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private OrderConverter orderConverter;

    public OrderServiceImpl(OrderDao orderDao, OrderConverter orderConverter) {
        this.orderDao = orderDao;
        this.orderConverter = orderConverter;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderConverter.convertEntityToDto(orderDao.getById(id));
    }

    @Override
    public OrderDto saveOrUpdate(OrderDto orderDto) {
        Order order = orderDao.saveOrUpdate(orderConverter.convertDtoToEntity(orderDto));
        return orderConverter.convertEntityToDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderConverter.convertEntitiesToDtos(orderDao.getAll());
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }
}
