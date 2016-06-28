package com.umland;

import com.umland.learnjava.generics.GenericsMain;

/**
 * Use this project to learn about things not really pertaining to code exactly.
 * For example git, Maven and Jenkins.
 */
public class Main {
	public static void main(String[] args) {
		System.out.println(CreateMessage());

		GenericsMain genericsMain = new GenericsMain();
		genericsMain.doIt();
	}

	/**
	 * @return String saying hello to the world.
	 */
	public static String CreateMessage() {
		return "Hello World";
	}
}
