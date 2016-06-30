package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersonLoader {
	private final RandomAccessFile file;

	public PersonLoader(final File file) throws FileNotFoundException {
		this.file = new RandomAccessFile(file, "rw");
	}

	public Person load() throws IOException {
		String line = file.readLine();
		String[] parts = line.split(",");

		String name = parts[0];
		Integer age = Integer.parseInt(parts[1]);

		return new Person(name, age);
	}
}
