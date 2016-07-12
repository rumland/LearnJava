package com.umland.learnjava.generics.erasure;

public class InstanceOf<T> {
    public boolean equals(Object obj) {
        //erasure
        if (obj instanceof InstanceOf/*<T>*/) {
            return true;
        }

        return false;
    }

    public static void main(String args[]) {
        System.out.println(new InstanceOf<>() instanceof InstanceOf);
        System.out.println(new InstanceOf<>() instanceof Object);
        /*
        Compile time error that Integer cannot be cast to InstanceOf.
        System.out.println(new InstanceOf<>() instanceof Integer);
         */
    }
}
