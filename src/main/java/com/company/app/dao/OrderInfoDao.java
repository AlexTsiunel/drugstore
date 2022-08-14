package com.company.app.dao;

import com.company.app.model.entity.Drug;
import com.company.app.model.entity.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderInfoDao extends AbstractDao<Long, OrderInfo>{

    List<OrderInfo> getByOrderId(Long id);
}
