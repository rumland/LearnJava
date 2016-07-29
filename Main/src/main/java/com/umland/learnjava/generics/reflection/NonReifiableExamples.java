package com.umland.learnjava.generics.reflection;

import java.util.ArrayList;
import java.util.List;

public class NonReifiableExamples<T> {
    public static void main(String[] args) {
        new NonReifiableExamples<String>().main();
    }

    private void main() {
//        System.out.println(T.class); // compile time error "Cannot select from a type variable"
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        System.out.println(strings.getClass());
        System.out.println(integers.getClass());
        System.out.println(strings.getClass() == integers.getClass());

        List<? extends Number> numbers = new ArrayList<>();
        System.out.println(numbers.getClass());
        System.out.println(strings.getClass() == numbers.getClass());
    }
}
