package com.umland.learnjava.generics.erasure;

public class InstanceOf<T> {
    public boolean equals(Object obj) {
        //erasure
        return obj instanceof InstanceOf;
//        if (obj instanceof InstanceOf/*<T>*/) {
//            return true;
//        }
//
//        return false;
    }

    public static void main(String args[]) {
        //noinspection ConstantConditions
        boolean x = new InstanceOf<>() instanceof InstanceOf;
        System.out.println(x);
        //noinspection ConstantConditions
        boolean x1 = new InstanceOf<>() instanceof Object;
        System.out.println(x1);
        /*
        Compile time error that Integer cannot be cast to InstanceOf.
        System.out.println(new InstanceOf<>() instanceof Integer);
         */
    }
}
