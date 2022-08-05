package com.company.app.dao;

import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.*;
import com.company.app.model.entity.OrderInfo;
import com.company.app.model.entity.RecipeInfo;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private final Map<Class<?>, Object> map;

    private static class DaoFactoryHolder{
        public static final DaoFactory HOLDER_INSTANCE = new DaoFactory();
    }

    private DaoFactory(){
        map = new HashMap<>();
        map.put(DrugDao.class, new DrugDaoImpl(DataSource.getInstance()));
        map.put(DoctorDao.class, new DoctorDaoImpl(DataSource.getInstance()));
        map.put(ClientDao.class, new ClientDaoImpl(DataSource.getInstance()));
        map.put(PharmacistDao.class, new PharmacistDaoImpl(DataSource.getInstance()));
        map.put(RecipeInfoDao.class, new RecipeInfoDaoImpl(DataSource.getInstance(), getDao(DrugDao.class)));
        map.put(OrderInfoDao.class, new OrderInfoDaoImpl(DataSource.getInstance(),getDao(DrugDao.class)));
        map.put(RecipeDao.class, new RecipeDaoImpl(DataSource.getInstance(), getDao(DoctorDao.class), getDao(ClientDao.class)));
        map.put(OrderDao.class, new OrderDaoImpl(DataSource.getInstance(), getDao(ClientDao.class), getDao(PharmacistDao.class), getDao(OrderInfoDao.class)));
    }
    public <T>T getDao(Class<T> clazz){
        return (T) map.get(clazz);
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.HOLDER_INSTANCE;
    }
}
