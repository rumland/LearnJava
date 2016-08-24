package com.umland.learnjava.reflection;

import com.umland.classesusedinexamples.Car;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;

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
        Class<?> cls = Car.class;
        Constructor constructor = cls.getConstructor(int.class);
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

    @Test
    public void getSuperClassTest() {
        Class<?> cls = Car.class;
        Class<?> superCls = cls.getSuperclass();
        while (superCls != null) {
            System.out.println(superCls.getName());
            superCls = superCls.getSuperclass();
        }
    }

    @Test
    public void getConstructorsTest() {
        Class<?> cls = Car.class;
        Constructor[] constructors = cls.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.printf("%s(%d - %s)%n", constructor.getName(), constructor.getParameterCount(),
                    getPrettyParams(constructor.getParameters()));
        }
    }

    @Test
    public void getFieldsTest() {
        Class<?> cls = Car.class;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.printf("%s:%s%n", field.getName(), field.getType());
        }
    }

    @Test
    public void getSpecificFieldTest() throws NoSuchFieldException {
        Class<?> cls = Car.class;
        Field field = cls.getDeclaredField("people");
        Class type = field.getType();
        System.out.printf("type: %s%n", type.getTypeName());
        System.out.printf("isArray: %b%n", type.isArray());
    }

    private void displayMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.printf("%s (parameter count: %d) [parameters: %s]%n", method.getName(),
                    method.getParameterCount(), getPrettyParams(method.getParameters()));
        }
    }

    private String getPrettyParams(Parameter[] parameters) {
        String params = "";
        for (Parameter parameter : parameters) {
            params += String.format("%s:%s, ", parameter.getName(), parameter.getType());
        }
        params = params.isEmpty() ? "N/A" : params.substring(0, params.lastIndexOf(", "));
        return params;
    }
}
