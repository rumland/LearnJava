package com.umland.learnjava.generics.implementgenerictype;

public class Person implements Comparable<Person> {
	private final String name;
	private final Integer age;

	public Person(String name, Integer age) {
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
}
