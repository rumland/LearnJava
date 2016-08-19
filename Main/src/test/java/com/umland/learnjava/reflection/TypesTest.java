package com.umland.learnjava.reflection;

import com.umland.classesusedinexamples.Car;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypesTest {
    /**
     * An exception is expected to be thrown because no default constructor exists for the Car class.
     * @throws IllegalAccessException things went wrong
     * @throws InstantiationException things went wrong
     */
    @Test(expected = InstantiationException.class)
    public void testTypeExceptionTest() throws IllegalAccessException, InstantiationException {
        Class cls = Car.class;
        cls.newInstance();
    }

    @Test
    public void testTypePassTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cls = Car.class;
        //TODO ru: understand unchecked cast problem here.
        Constructor<?> constructor = cls.getConstructor((Class<?>)int.class);
        int numberOfDoors = 2;
        Car car = (Car) constructor.newInstance(numberOfDoors);
        Assert.assertEquals(String.format("Expected %s doors.", numberOfDoors), numberOfDoors, car.getDoors());
    }

    @Test
    public void publicMethodsTest() {
        Class cls = Car.class;
        Method[] methods = cls.getMethods();
        displayMethods(methods);
    }

    @Test
    public void declaredMethodsTest() {
        Class cls = Car.class;
        Method[] methods = cls.getDeclaredMethods();
        displayMethods(methods);
    }

    //TODO ru: return to looking at types @ 3:58


    private void displayMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.printf("%s (parameter count: %d)%n", method.getName(), method.getParameterCount());
        }
    }
}
