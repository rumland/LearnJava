package com.umland;

import com.umland.learnjava.generics.GenericClassRunner;
import com.umland.learnjava.generics.PersonMain;

/**
 * Use this project to learn about things not really pertaining to code exactly.
 * For example git, maven and jenkins.
 */
public class Main {
	/**
	 * Classic beginners program
	 *
	 * @param args N/A
	 */
	public static void main(String[] args) {
		System.out.println(CreateMessage());

		GenericClassRunner cbm = new GenericClassRunner();
		cbm.doIt();

		PersonMain pm = new PersonMain();
		pm.doIt();
	}

	/**
	 * @return String saying hello to the world.
	 */
	public static String CreateMessage() {
		return "Hello World";
	}
}
