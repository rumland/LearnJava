package com.umland.learnjava.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonMain {
	public void doIt() {
		List<Person> personList = new ArrayList<>(Arrays.asList(
				new Person("Robert Umland", 32),
				new Person("Jennifer Umland", 32),
				new Person("Karl Umland", 30),
				new Person("Alyssa Umland", 1)));
		for (Person person : personList) {
			System.out.println(person);
		}

		System.out.println("Before sort: " + personList);

		Collections.sort(personList, new ImplementGenericType());
		System.out.println("After age sort: " + personList);

		Collections.sort(personList, new PassingParameterToGenericType<>(new ImplementGenericType()));
		System.out.println("After reverse age sort: " + personList);

		TypeBounds<Person> sp = new TypeBounds<>(personList.get(0), personList.get(2));
		System.out.println("First: " + sp.getFirst());
		System.out.println("Second: " + sp.getSecond());
		Person youngestPerson = GenericMethods.min(personList, new ImplementGenericType());
		System.out.println("Youngest person: " + youngestPerson);
		Person oldestPerson = GenericMethods.min(personList, new PassingParameterToGenericType<>(new ImplementGenericType()));
		System.out.println("Oldest person: " + oldestPerson);

		Map<String, Person> personMap = new HashMap<>();
		personMap.put(personList.get(0).getName(), personList.get(0));
		personMap.put(personList.get(0).getName(), personList.get(0));
		personMap.put(personList.get(1).getName(), personList.get(1));

		System.out.println(personMap);
	}
}
