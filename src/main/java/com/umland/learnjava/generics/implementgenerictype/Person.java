package com.umland.learnjava.generics.implementgenerictype;

import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {
	private final String name;
	private final int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("Person{name='%s', age=%s}", this.getName(), this.getAge());
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	@Override
	public int compareTo(Person o) {
		return this.getAge().compareTo(o.getAge());
	}

    @Override
	public boolean equals(Object other) {
        boolean result = false;
        if (other == null || (getClass() != other.getClass())) {
            result = false;
        } else {
            Person otherPerson = (Person) other;
            result = this.getAge().equals(otherPerson.getAge()) && this.getName().equals(otherPerson.getName());
        }

        return result;
	}
}
