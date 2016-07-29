package com.umland.learnjava.generics.implementgenerictype;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    private Integer age;
    private String name = "name";
    private Person person;

    @Before
    public void setUp() {
        age = 10;
        name = "name";
        person = new Person(name, age);
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(String.format("Person{name='%s', age=%s}", name, age), person.toString());
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals(name, person.getName());
    }

    @Test
    public void testGetAge() throws Exception {
        Assert.assertEquals(age, person.getAge());
    }

    @Test
    public void testCompareTo() throws Exception {
        Person person2 = new Person("name2", 11);

        Assert.assertEquals(-1, person.compareTo(person2));
        Assert.assertEquals(0, person.compareTo(person));
        Assert.assertEquals(1, person2.compareTo(person));
    }

//    If it implements readObject( ), it should implement writeObject( ), and vice-versa.
//    It is equal (using the equals( )method) to a serialized copy of itself.
//    It has the same hashcode as a serialized copy of itself.
}