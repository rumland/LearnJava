package com.umland.learnjava.generics.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReifiableExamples {
    public static void main (String[] args) {
        System.out.printf("%s%n", int.class);
        System.out.printf("%s%n", String.class);
        System.out.printf("%s%n", ReifiableExamples.class);
        List<?> wildcards = new ArrayList();
        System.out.printf("%s%n", wildcards);
        System.out.printf("%s%n", wildcards.getClass());
        List raw = new ArrayList();
        System.out.printf("%s%n", raw.getClass());
        System.out.printf("%s.getClass() == %s.getClass() : %s", "wildcards", "raw", wildcards.getClass() == raw.getClass());
        System.out.printf("%s%n", int[].class);
        System.out.printf("%s%n", List.class);

        new ReifiableExamples().reflectingGenericInformation();
    }

    private void reflectingGenericInformation() {
        System.out.println("reflectingGenericInformation");
        List<String> strings = new ArrayList<>();
        Class<?> arrayList = strings.getClass();
        final TypeVariable<? extends Class<?>>[] typeParameters = arrayList.getTypeParameters();
        System.out.println(arrayList);
        System.out.println(Arrays.toString(typeParameters));
        System.out.println(arrayList.toString());
        System.out.println(arrayList.toGenericString());

        System.out.println(StringList.class.getGenericSuperclass());
        ParameterizedType arrayListOfString = (ParameterizedType) StringList.class.getGenericSuperclass();
        System.out.println(Arrays.toString(arrayListOfString.getActualTypeArguments()));
    }

    public static class StringList extends ArrayList<String> { }
}
