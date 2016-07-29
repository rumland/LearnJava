package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;

public class Partner extends Person {
	public Partner(String name, Integer age) {
		super(name, age);
	}

	@Override
	public String toString() {
		return String.format("Partner{name='%s', age='%s'}",
				super.getName(), super.getAge());
	}
}
