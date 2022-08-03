package com.company.app.model.converter;

import java.util.HashMap;
import java.util.Map;

public class ConverterFactory {
    private final Map<Class<?>, Object> map;
    private static ConverterFactory INSTANCE;


    private ConverterFactory() {
        map = new HashMap<>();
        map.put(ClientConverter.class, new ClientConverter(getConverter(OrderConverter.class), getConverter(RecipeConverter.class)));
        map.put(DoctorConverter.class, new DoctorConverter(getConverter(RecipeConverter.class)));
        map.put(DrugConverter.class, new DrugConverter());
        map.put(OrderConverter.class, new OrderConverter(getConverter(ClientConverter.class), getConverter(DrugConverter.class), getConverter(PharmacistConverter.class)));
        map.put(PharmacistConverter.class, new PharmacistConverter(getConverter(OrderConverter.class)));
        map.put(RecipeConverter.class, new RecipeConverter(getConverter(ClientConverter.class), getConverter(DoctorConverter.class), getConverter(DrugConverter.class)));
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
