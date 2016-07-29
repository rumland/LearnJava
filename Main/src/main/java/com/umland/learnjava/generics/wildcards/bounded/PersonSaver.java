package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class PersonSaver {
	private final File file;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

	public PersonSaver(final File file) throws FileNotFoundException {
		this.file = file;
	}

    public void open() {
        try {
            fos = new FileOutputStream(file, true /*append*/);
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void save(Person person) throws IOException {
        oos.writeObject(person);
	}

	void saveAll(final Person[] persons) throws IOException {
        List<Person> p = Arrays.asList(persons);
        saveAllUpperBound(p);
	}

	void saveAll(final List<Person> persons) throws IOException {
		Person[] personsArray = (Person[]) persons.toArray();

		saveAll(personsArray);
	}

    void saveAllUpperBound(final List<? extends Person> persons) throws IOException {
        for (Person person : persons) {
            save(person);
        }
    }

    <T extends Person> void saveAllGenericType(final List<T> persons) throws IOException {
        for (Person person : persons) {
            save(person);
        }
    }
}
