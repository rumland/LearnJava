package com.umland.learnjava.generics;

import com.umland.learnjava.generics.clazz.CircularBuffer;
import com.umland.learnjava.generics.passparametertogenerictype.ReverseComparator;
import com.umland.learnjava.generics.methods.GenericMethods;
import com.umland.learnjava.generics.typebounds.SortedPair;
import com.umland.learnjava.generics.implementgenerictype.AgeComparator;
import com.umland.learnjava.generics.implementgenerictype.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericsMain {
	public void doIt() {
		List<Person> personList = getPersons();

		showImplementGenericType(personList);

		showPassParameterToGenericType(personList);

		showTypeBounds(personList);

		showGenericMethod(personList);

		showGenericClass();
	}

	private List<Person> getPersons() {
		List<Person> personList;
		personList = new ArrayList<>(Arrays.asList(
				new Person("Robert Umland", 32),
				new Person("Jennifer Umland", 32),
				new Person("Karl Umland", 30),
				new Person("Alyssa Umland", 1)));
		for (Person person : personList) {
			System.out.println(person);
		}
		return personList;
	}

	private void showImplementGenericType(List<Person> personList) {
		System.out.println("showImplementGenericType");
		System.out.println("Before sort: " + personList);
		
		Collections.sort(personList, new AgeComparator());
		System.out.println("After age sort: " + personList);
	}

	private void showPassParameterToGenericType(List<Person> personList) {
		System.out.println("showPassParameterToGenericType");
		Collections.sort(personList, new ReverseComparator<>(new AgeComparator()));
		System.out.println("After reverse age sort: " + personList);
	}

	private void showTypeBounds(List<Person> personList) {
		System.out.println("showTypeBounds");
		SortedPair<Person> sp = new SortedPair<>(personList.get(0), personList.get(2));
		System.out.println("First: " + sp.getFirst());
		System.out.println("Second: " + sp.getSecond());
	}

	private void showGenericMethod(List<Person> personList) {
		System.out.println("showGenericMethod");
		Person youngestPerson = GenericMethods.min(personList, new AgeComparator());
		System.out.println("Youngest person: " + youngestPerson);
		Person oldestPerson = GenericMethods.min(personList, new ReverseComparator<>(new AgeComparator()));
		System.out.println("Oldest person: " + oldestPerson);
	}

	private void showGenericClass() {
		System.out.println("showGenericClass");
		CircularBuffer<String> buffer = new CircularBuffer<>(10);
		
		buffer.offer("a");
		buffer.offer("bc");
		buffer.offer("d");

		String value = concatenate(buffer);

		buffer.offer("a");
		buffer.offer("bc");
		buffer.offer("d");
		System.out.println("Value: " + value);
	}

	private String concatenate(CircularBuffer<String> buffer) {
		StringBuilder builder = new StringBuilder();

		String value = buffer.poll();
		while (value != null) {
			System.out.println("value: " + value);
			builder.append(value);
			value = buffer.poll();
		}

		return builder.toString();
	}
}
