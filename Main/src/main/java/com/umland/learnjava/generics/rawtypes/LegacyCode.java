package com.umland.learnjava.generics.rawtypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LegacyCode {
    public static void main(String args[]) {
        List list = new ArrayList();
        //noinspection unchecked
        list.add("abc");
        //noinspection unchecked
        list.add(1);
        //noinspection unchecked
        list.add(new Object());

        for (Object element : list) {
            System.out.println("element: " + element);
        }

        /*
        Can assign from any raw type to any generic type.
        Can assign from any generic type to any raw type.
        The below example shows how ClassCastException is thrown.
         */
        try {
            //noinspection unchecked,UnnecessaryLocalVariable
            List<String> listCopy = list;
            for (String elem : listCopy) {
                System.out.println("elem: " + elem);
            }
        } catch (ClassCastException e) {
            System.out.println("rawtypes can cause runtime exceptions");
        }
    }
}
