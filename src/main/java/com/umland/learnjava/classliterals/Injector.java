package com.umland.learnjava.classliterals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private Map<Class<?>, Object> objectGraph = new HashMap<>();

    public Injector with(Object value) {
        objectGraph.put(value.getClass(), value);
        return this;
    }

    public <T> T newInstance(final Class<T> type) {
        return (T) objectGraph.computeIfAbsent(type, this::instantiate);
    }

    private Object instantiate(Class<?> type) {
        Constructor<?>[] constructors = type.getConstructors();

        if (constructors.length != 1) {
            throw new IllegalArgumentException(String.format("%s type must have only one constructor."));
        }

        Constructor ctor = constructors[0];

        if (!objectGraph.containsKey(type)) {
            Object newObject = null;
            try {
                newObject = ctor.newInstance("Bad example from Pluralsight...");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            objectGraph.put(type, newObject);
        }

        return objectGraph.get(type);
    }
}
