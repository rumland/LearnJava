package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;

public class Employee extends Person {
	public Employee(String name, Integer age) {
		super(name, age);
	}

	@Override
	public String toString() {
		return String.format("Employee{name='%s', age='%s'}",
				super.getName(), super.getAge());
	}
}
