package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonStorageTest {
	private Partner donDrapper = new Partner("Don Drapper", 89);
	private Partner bertCooper = new Partner("Bert Cooper", 100);
	private Employee peggyOlson = new Employee("Peggy Olson", 65);

	private File file;
	private PersonSaver saver;
	private PersonLoader loader;
	
	@Test
	public void testSavesAndLoadsPerson() throws IOException {
		Person person = new Person("Bob", 20);

		saver.save(person);

		assertEquals(person, loader.load());
	}
	
	@Test
	public void testSavesAndLoadsArraysOfPeople() throws IOException {
		Person[] persons = new Person[]{bertCooper, peggyOlson};

		saver.saveAll(persons);

		assertEquals(bertCooper, loader.load());
		assertEquals(peggyOlson, loader.load());
	}

	@Test
	public void testSavesAndLoadsArraysOfPartners() throws IOException {
		Partner[] persons = new Partner[]{bertCooper, donDrapper};

		saver.saveAll(persons);

		assertEquals(bertCooper, loader.load());
		assertEquals(donDrapper, loader.load());
	}

	@Test
	public void testSavesAndLoadsListOfPeople() throws IOException {
		List<Person> persons = new ArrayList<Person>(Arrays.asList(bertCooper, donDrapper));

		saver.saveAll(persons);

		assertEquals(bertCooper, loader.load());
		assertEquals(donDrapper, loader.load());
	}
}
