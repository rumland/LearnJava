package com.umland.learnjava.jvmclassloading;

import com.umland.BarnInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassUrlLoading {
    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
            Class<?> klass = urlClassLoader.loadClass("com.umland.Barn");
            BarnInterface barn = (BarnInterface) klass.newInstance();
            Constructor stringConstructor = klass.getConstructor(String.class);
            BarnInterface yellowBarn = (BarnInterface) stringConstructor.newInstance("yellow");
            System.out.println(barn.getColor());
            System.out.println(yellowBarn.getColor());
        } catch (MalformedURLException | ClassNotFoundException | InstantiationException |
                IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
