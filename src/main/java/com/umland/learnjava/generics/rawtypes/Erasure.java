package com.umland.learnjava.generics.rawtypes;

import java.util.List;

/**
 * To show all classes and methods produced by javac run the following:
 * <pre>
 *     {@code
 *     Roberts-MBP:LearnJava rumland$ pwd
 *     /Users/rumland/Documents/Development/LearnJava
 *     Roberts-MBP:LearnJava rumland$ javap -s -classpath target/classes com.umland.learnjava.generics.rawtypes.Erasure
 *     }
 * </pre>
 *
 * To decompile byte code produced by javac run the following (notice -b option):
 * <pre>
 *     {@code
 *     Roberts-MBP:LearnJava rumland$ pwd
 *     /Users/rumland/Documents/Development/LearnJava
 *     Roberts-MBP:LearnJava rumland$ javap -s -c -classpath target/classes com.umland.learnjava.generics.rawtypes.Erasure
 *     }
 * </pre>
 *
 * @param <T> type
 * @param <B> comparator
 */
public class Erasure<T, B extends Comparable<B>> {
    public void unbounded(T param) {
    }

    public void lists(List<String> param, List<List<T>> param2) {
        String s = param.get(0);
    }

    public void bounded(B param) {
    }
}
