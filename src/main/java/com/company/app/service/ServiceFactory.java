package com.company.app.service;

import com.company.app.dao.*;
import com.company.app.model.converter.*;
import com.company.app.service.impl.*;

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
        map.put(DoctorService.class, new DoctorServiceImpl(DaoFactory.getInstance().getDao(DoctorDao.class), ConverterFactory.getInstance().getConverter(DoctorConverter.class)));
        map.put(DrugService.class, new DrugServiceImpl(DaoFactory.getInstance().getDao(DrugDao.class), ConverterFactory.getInstance().getConverter(DrugConverter.class)));
        map.put(PharmacistService.class, new PharmacistServiceImpl(DaoFactory.getInstance().getDao(PharmacistDao.class), ConverterFactory.getInstance().getConverter(PharmacistConverter.class)));
        map.put(RecipeService.class, new RecipeServiceImpl(DaoFactory.getInstance().getDao(RecipeDao.class), ConverterFactory.getInstance().getConverter(RecipeConverter.class)));
        map.put(OrderService.class, new OrderServiceImpl(DaoFactory.getInstance().getDao(OrderDao.class), DaoFactory.getInstance().getDao(DrugDao.class), ConverterFactory.getInstance().getConverter(OrderConverter.class), ConverterFactory.getInstance().getConverter(DrugConverter.class)));
    }


    public <T> T getService(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.HOLDER_INSTANCE;
    }
}
