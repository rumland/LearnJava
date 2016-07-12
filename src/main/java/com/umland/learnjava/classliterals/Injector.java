package com.umland.learnjava.classliterals;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private Map<Class<?>, Object> objectGraph = new HashMap<>();

    public Injector with(Object value) {
        objectGraph.put(value.getClass(), value);
        return this;
    }

    public <T> T getInstance(final Class<T> type) {
        return (T) objectGraph.computeIfAbsent(type, this::instantiate);
    }

    //TODO ru: July 12, 2016 -- Last here. Start with Reflection Types after trying to flesh out this function.
    private Object instantiate(Class<?> type) {
        try {
            Constructor<?>[] constructors = type.getConstructors();

            if (constructors.length != 1) {
                throw new IllegalArgumentException(String.format("%s type must have only one constructor."));
            }
        }
    }
}
