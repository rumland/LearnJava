package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class PersonSaver {
	private final RandomAccessFile file;

	public PersonSaver(final File file) throws FileNotFoundException {
		this.file = new RandomAccessFile(file, "rw");
	}

	public void save(Person person) throws IOException {
		this.file.writeChars(person.toString());
	}

	void saveAll(Person[] persons) throws IOException {
		for(Person person : persons) {
			save(person);
		}
	}

	void saveAll(List<Person> persons) throws IOException {
		Person[] personsArray = (Person[]) persons.toArray();

		saveAll(personsArray);
	}
}
