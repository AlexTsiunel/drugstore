package com.company.app.service;

import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;

import java.util.Map;

public interface OrderService extends AbstractService<Long, OrderDto>{
    public OrderDto created(Map<Long, Integer> map, ClientDto clientDto);
}
