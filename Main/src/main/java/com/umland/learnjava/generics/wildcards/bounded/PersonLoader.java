package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;

import java.io.*;
import java.util.List;

public class PersonLoader {
	private final File file;
    private ObjectInputStream ois;
    private FileInputStream fis;

	public PersonLoader(final File file) {
        this.file = file;
    }

    public void open() throws IOException {
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
    }

    public void close() {
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public Person load() {
        Person person = null;
        try {
            person = (Person) ois.readObject();
        } catch (EOFException e) {
            //Do nothing
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void loadAll(List<? super Person> persons) throws IOException {
        Person person;
        do {
            person = load();
            if (person != null) {
                persons.add(person);
            }
        } while (person != null);
    }
}
