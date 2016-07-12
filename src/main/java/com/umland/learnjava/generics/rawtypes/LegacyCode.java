package com.umland.learnjava.generics.rawtypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LegacyCode {
    public static void main(String args[]) {
        List list = new ArrayList();
        list.add("abc");
        list.add(1);
        list.add(new Object());

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            final Object element = iterator.next();

            System.out.println("element: " + element);
        }

        /*
        Can assign from any raw type to any generic type.
        Can assign from any generic type to any raw type.
        The below example shows how ClassCastException is thrown.
         */
        try {
            List<String> listCopy = list;
            for (String elem : listCopy) {
                System.out.println("elem: " + elem);
            }
        } catch (ClassCastException e) {
            System.out.println("rawtypes can cause runtime exceptions");
        }
    }
}
