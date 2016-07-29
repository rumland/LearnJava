package com.umland.learnjava.junit.customannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SnoozeAnnotation {
	String date();
}
