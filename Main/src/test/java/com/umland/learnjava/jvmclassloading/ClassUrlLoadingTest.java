package com.umland.learnjava.jvmclassloading;

import com.umland.BarnInterface;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Demonstrate various ways to load a class.
 *     <ol>
 *         <li>Class.forName</li>
 *         <li>URLClassLoader using default constructor</li>
 *         <li>URLClassLoader using specified constructor according to signature</li>
 *         <li>Custom class loader, from a database, for example.
 *             This was not written because don't know how to manage a database on OSX.</li>
 *     </ol>
 * <p>
 * When explicitly loading a class, rather than relying on {@link Delegation}, one
 * really should use an interface. The following example is "wrong".
 * <pre>
 *      {@code
 *          Class rocketClass = Class.forName("Rocket", true, urlClassLoader);
 *          Rocket r = (Rocket) rocketClass.newInstance();}
 * </pre>
 * <p>
 * The following example is "correct".
 * <pre>
 *     {@code
 *         Class rocketClass = Class.forName("Rocket", true, urlClassLoader);
 *         RocketInterface r = (RocketInterface) rocketClass.newInstance();
 *     }
 * </pre>
 * <p>
 * It is correct assuming that the interface is defined in a jar different than the class. This
 * allows someone to load multiple versions of the same class and use the same interface.
 */
public class ClassUrlLoadingTest {
    @Test
    public void classForNameTest() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL url = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

        Class<?> barnClass = Class.forName("com.umland.Barn", true, urlClassLoader);
        Constructor<?> barnConstructor = barnClass.getConstructor(String.class);

        BarnInterface greenBarn = (BarnInterface) barnConstructor.newInstance("green");

        Assert.assertEquals("green", greenBarn.getColor());
    }

    @Test
    public void multipleClassForNameTest() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL url1 = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[]{url1});
        Class<?> barnClass1 = Class.forName("com.umland.Barn", true, urlClassLoader1);
        Constructor<?> barnConstructor1 = barnClass1.getConstructor(String.class);
        BarnInterface greenBarn = (BarnInterface) barnConstructor1.newInstance("green");

        URL url2 = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader2 = new URLClassLoader(new URL[]{url2});
        Class<?> barnClass2 = Class.forName("com.umland.Barn", true, urlClassLoader2);
        Constructor<?> barnConstructor2 = barnClass2.getConstructor(String.class);
        BarnInterface goldBarn = (BarnInterface) barnConstructor2.newInstance("gold");

        Assert.assertEquals("green", greenBarn.getColor());
        Assert.assertEquals("gold", goldBarn.getColor());
        Assert.assertNotEquals(barnClass1, barnClass2);
        Assert.assertNotEquals(greenBarn.getClass(), goldBarn.getClass());
    }

    @Test
    public void urlClassLoaderDefaultConstructorTest() throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        URL url = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

        Class<?> klass = urlClassLoader.loadClass("com.umland.Barn");

        BarnInterface barn = (BarnInterface) klass.newInstance();

        Assert.assertEquals("red", barn.getColor());
    }

    @Test
    public void urlClassLoaderSpecificConstructorTest() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL url = new URL("file:///Users/robertumland/Documents/Development/GitHub/LearnJava/Farm/target/Farm-1.0-SNAPSHOT.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

        Class<?> klass = urlClassLoader.loadClass("com.umland.Barn");

        Constructor stringConstructor = klass.getConstructor(String.class);
        BarnInterface yellowBarn = (BarnInterface) stringConstructor.newInstance("yellow");

        Assert.assertEquals("yellow", yellowBarn.getColor());
    }
}