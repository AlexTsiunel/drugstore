package com.company.app.model.converter;

import java.util.HashMap;
import java.util.Map;

public class ConverterFactory {
    private final Map<Class<?>, Object> map;
    private static ConverterFactory INSTANCE;


    private ConverterFactory() {
        map = new HashMap<>();
        map.put(DrugConverter.class, new DrugConverter());
        map.put(ClientConverter.class, new ClientConverter());
        map.put(DoctorConverter.class, new DoctorConverter());
        map.put(PharmacistConverter.class, new PharmacistConverter());
        map.put(OrderConverter.class, new OrderConverter(getConverter(DrugConverter.class)));
        map.put(RecipeConverter.class, new RecipeConverter(getConverter(ClientConverter.class), getConverter(DoctorConverter.class)));
    }
    public static ConverterFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ConverterFactory();
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> T getConverter(Class<T> clazz) {
        return (T) map.get(clazz);
    }
}
