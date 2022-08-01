package com.company.app.dao;

import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.ClientDaoImpl;
import com.company.app.dao.impl.DrugDaoImpl;

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
        map.put(ClientDao.class, new ClientDaoImpl(DataSource.getInstance()));
    }
    public <T>T getDao(Class<T> clazz){
        return (T) map.get(clazz);
    }

    public static DaoFactory getInstance(){
        return DaoFactoryHolder.HOLDER_INSTANCE;
    }
}
