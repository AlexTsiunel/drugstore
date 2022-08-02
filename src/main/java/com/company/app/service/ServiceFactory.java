package com.company.app.service;

import com.company.app.dao.ClientDao;
import com.company.app.dao.DaoFactory;
import com.company.app.dao.DrugDao;
import com.company.app.model.converter.ClientConverter;
import com.company.app.model.converter.ConverterFactory;
import com.company.app.model.converter.DrugConverter;
import com.company.app.service.impl.ClientServiceImpl;
import com.company.app.service.impl.DrugServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private final Map<Class<?>, Object> map;

    private static class ServiceFactoryHolder {
        public static final ServiceFactory HOLDER_INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {
        map = new HashMap<>();
        map.put(ClientService.class, new ClientServiceImpl(DaoFactory.getInstance().getDao(ClientDao.class), ConverterFactory.getInstance().getConverter(ClientConverter.class)));
        map.put(DrugService.class, new DrugServiceImpl(DaoFactory.getInstance().getDao(DrugDao.class), ConverterFactory.getInstance().getConverter(DrugConverter.class)));
    }


    public <T> T getService(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.HOLDER_INSTANCE;
    }
}
