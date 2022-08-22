package com.company.app.service.paging;

import java.util.HashMap;
import java.util.Map;

public class PagingFactory {
    private final Map<Class<?>, Object> map;

    private static class PagingFactoryHolder {
        public static final PagingFactory HOLDER_INSTANCE = new PagingFactory();
    }

    private PagingFactory() {
        map = new HashMap<>();
        map.put(PagingUtil.class, PagingUtil.getInstance());
    }

    public <T> T getPaging(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    public static PagingFactory getInstance() {
        return PagingFactoryHolder.HOLDER_INSTANCE;
    }
}
