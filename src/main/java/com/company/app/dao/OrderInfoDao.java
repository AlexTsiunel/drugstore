package com.company.app.dao;

import com.company.app.model.entity.Drug;
import com.company.app.model.entity.OrderInfo;

import java.util.Map;

public interface OrderInfoDao extends AbstractDao<Long, OrderInfo>{

    Map<Drug, Integer> getMapDrugsByOrderId(Long id);
}
