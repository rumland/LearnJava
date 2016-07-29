package com.umland.learnjava.generics.wildcards.unbounded;

import com.umland.learnjava.generics.implementgenerictype.Person;

import java.util.ArrayList;
import java.util.List;

public class UnboundedUsage {
    public static void main (String args[]) {
        List<Object> objects1 = new ArrayList<>();
        objects1.add(new Object());
        objects1.add(new Person("Alyssa Umland", 1));
        System.out.println(objects1);

        List<? super Object> objects2 = new ArrayList<>();
        objects2.add(new Object());
        objects2.add(new Person("Alyssa Umland", 1));
        System.out.println(objects2);
    }
}
