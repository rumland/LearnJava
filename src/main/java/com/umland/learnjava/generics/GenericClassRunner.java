package com.umland.learnjava.generics;

public class GenericClassRunner {
	public void doIt() {
		GenericClassCircularBuffer<String> buffer = new GenericClassCircularBuffer<>(10);

		buffer.offer("a");
		buffer.offer("bc");
		buffer.offer("d");

		String value = concatenate(buffer);

		buffer.offer("a");
		buffer.offer("bc");
		buffer.offer("d");
		System.out.println("Value: " + value);
	}

	private String concatenate(GenericClassCircularBuffer<String> buffer) {
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
