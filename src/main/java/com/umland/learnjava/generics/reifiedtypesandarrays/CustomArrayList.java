package com.umland.learnjava.generics.reifiedtypesandarrays;

import java.util.AbstractList;

public class CustomArrayList<T> extends AbstractList<T> {
    private Object[] values;

    public CustomArrayList() {
        values = new Object[0];
    }

    public T get(final int index) {
        return (T) values[index];
    }

    public boolean add(final T obj) {
        Object[] newValues = new Object[size() + 1];

        for (int idx = 0; idx < size(); ++idx) {
            newValues[idx] = values[idx];
        }

        newValues[size()] = obj;
        values = newValues;

        return true;
    }

    public int size() {
        return values.length;
    }
}
