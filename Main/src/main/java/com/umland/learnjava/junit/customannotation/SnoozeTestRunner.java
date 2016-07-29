package com.umland.learnjava.junit.customannotation;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class SnoozeTestRunner extends BlockJUnit4ClassRunner {

	public SnoozeTestRunner(final Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		// First, get the base list of tests
		final List<FrameworkMethod> allTestMethods = getTestClass()
				.getAnnotatedMethods(Test.class);
		if (allTestMethods == null || allTestMethods.isEmpty()) {
			return allTestMethods;
		}

		// Filter the list down
		final List<FrameworkMethod> filteredMethods = new ArrayList<>(allTestMethods.size());
		for (final FrameworkMethod method : allTestMethods) {
			final SnoozeAnnotation snoozeAnnotation = method
					.getAnnotation(SnoozeAnnotation.class);
			if (snoozeAnnotation != null) {
				final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
				final ParsePosition parsePosition = new ParsePosition(0);
				Date snoozeDate = dateFormat.parse(snoozeAnnotation.date(), parsePosition);
				Date now = new Date();
				if (now.after(snoozeDate)) {
					System.out.println(String.format("Including test %s because no longer snoozing.", method.getName()));
					addMethodByDefault(filteredMethods, method);
				} else {
					System.out.println(String.format("Skipping test %s because still snoozing.", method.getName()));
				}
			} else {
				System.out.println(String.format("Including test %s because no snooze annotation.", method.getName()));
				addMethodByDefault(filteredMethods, method);
			}
		}

		return filteredMethods;
	}

	private void addMethodByDefault(final List<FrameworkMethod> filteredMethods, final FrameworkMethod method) {
		filteredMethods.add(method);
	}
}
