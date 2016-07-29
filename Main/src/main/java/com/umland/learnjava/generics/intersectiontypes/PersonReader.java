package com.umland.learnjava.generics.intersectiontypes;

import com.umland.learnjava.generics.implementgenerictype.Person;
import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PersonReader {
    final static String streamFile = "src/main/resources/person";

    /**
     * Demonstrate two different uses of type intersection, namely:
     * <ol>
     *   <li>
     *       Specify a missing interface where you do _not_ have controll over the source. For example, a missing
     *       interface in the core java library. This type of example is shown in the read method.
     *   </li>
     *   <li>
     *      Enable compatibility. For example, if you look at Collections.max's signature:
     *      {@code
     *        static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll)
     *      }
     *      Why, does T extend both Object and Comparable? Originally the max function was written as follows.
     *      {@code
     *        Object max(Collection coll)
     *      }
     *      Because of type erasure, if T extended only
     *      {@code
     *        Comparable<? super T>
     *      }
     *      then when T was erased you would have a type of Comparable. To enable backwards
     *      with the original implementation you need to extend both Object and Comparable.
     *   </li>
     * </ol>
     *
     * @param args Not really used. Here just because we have to?
     * @throws FileNotFoundException If the constant stream resource location cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        PersonReader reader = new PersonReader();

        reader.writePersonTofile(new Person("Robert Umland", 32));

        DataInputStream stream = new DataInputStream(new FileInputStream(streamFile));
        Person person = reader.read(stream);
        System.out.println(person);

        RandomAccessFile randomAccessFile = new RandomAccessFile(streamFile, "rw");
        Person person1 = reader.read(randomAccessFile);
        System.out.println(person1);

        // Here just so you can look at the java documentation for Collections.max
        System.out.printf("Max of 1 and 2: %s%n", Collections.max(new ArrayList<>(Arrays.asList(1, 2))));

        Assert.assertEquals(person, person1);
    }

    /**
     * This is an example of using type intersection to create a missing interface. In this specific example this
     * shows an interface that is both Closeable and DataInput.
     * @param stream Resource that is Closeable and DataInput to read a Person from.
     * @param <T> Object that is both Closeable and DataInput
     * @return Person that is stored in stream
     */
    public <T extends Closeable & DataInput> Person read(final T stream) {
        try (T input = stream) {
            String name = input.readUTF();
            int age = input.readInt();
            return new Person(name, age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Used to write a person to a file. Did this because I did not know the format of a file that can be parsed by
     * DataInputStream.
     *
     * @param person Person that will be written to file
     * @throws FileNotFoundException If for whatever reason the constant file location cannot be found...
     */
    public void writePersonTofile(Person person) throws FileNotFoundException {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(streamFile))) {
            output.writeUTF(person.getName());
            output.writeInt(person.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
