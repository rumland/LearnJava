package com.umland.learnjava.generics.erasure;

/**
 * Making this class generic results in a compile time error stating generic class may not extend Throwable.
 */
public class UncompilableException/*<T>*/ extends Exception {
    public static void main(String args[]) {
        try {
            throw new UncompilableException();
        } catch (UncompilableException/*<Integer>*/ e) {
            /*
            Cannot have generic type because at runtime the generic type has been erased.
             */
            e.printStackTrace();
        }
    }
}
