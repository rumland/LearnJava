package com.umland.learnjava.generics.wildcards.bounded;

import com.umland.learnjava.generics.implementgenerictype.Person;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

/*
TODO ru: Hopefully one day I understand the language enough to remove the loader.open and loader.close calls that
         are sprinkled throughout the code. Ideally class instantiation would open the resource and it would be
         closed on destruction. Also need to address the concurent nature of the shared resource between PersonSaver
         and PersonLoader. Really should return to this if I can ever remember to...
*/

/**
 * Wildcards are not a single feature, rather they are a name given to a collection of different capabilities.
 * <ol>
 *     <li>upper bounded: List&lt;? extends classname&gt;
 *     <li>lower bounded: List&lt;? super classname&gt;
 *     <li>unbounded    : List&lt;?&gt;
 * </ol>
 */
public class PersonStorageTest {
	@Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private Partner donDrapper = new Partner("Don Drapper", 89);
	private Partner bertCooper = new Partner("Bert Cooper", 100);
	private Employee peggyOlson = new Employee("Peggy Olson", 65);

	private PersonSaver saver;
	private PersonLoader loader;

    @Before
    public void setUp() throws IOException {
        File file = temporaryFolder.newFile();
        saver = new PersonSaver(file);
        saver.open();

        loader = new PersonLoader(file);
    }

    @After
    public void tearDown() {
        saver.close();
    }
	
	@Test
	public void testSavesAndLoadsPerson() throws IOException {
		Person person = new Person("Bob", 20);

		saver.save(person);

        loader.open();
        Person loadedPerson = loader.load();
		Assert.assertEquals(person, loadedPerson);
        loader.close();
	}

    @Test
    public void testSavesAndLoadsEmployee() throws IOException {
        saver.save(peggyOlson);

        loader.open();
        Person loadedPerson = loader.load();
        Assert.assertEquals(peggyOlson, loadedPerson);
        loader.close();
    }

    @Test
    public void testSavesAndLoadsPartner() throws IOException {
        saver.save(bertCooper);

        loader.open();
        Person loadedPerson = loader.load();
        Assert.assertEquals(bertCooper, loadedPerson);
        loader.close();
    }

    @Test
	public void testSavesAndLoadsArraysOfPeople() throws IOException {
        Person[] persons = new Person[]{bertCooper, peggyOlson};

        saver.saveAll(persons);

        loader.open();
        Assert.assertEquals(bertCooper, loader.load());
        Assert.assertEquals(peggyOlson, loader.load());
        loader.close();
    }

    /**
     * This test demonstrates how Java arrays are covariant. This is unsafe.
     * @throws IOException
     */
	@Test
	public void testSavesAndLoadsArraysOfPartners() throws IOException {
		Partner[] partners = new Partner[]{bertCooper, donDrapper};

		saver.saveAll(partners);

        loader.open();
		Assert.assertEquals(bertCooper, loader.load());
		Assert.assertEquals(donDrapper, loader.load());
        loader.close();
	}

    /**
     * This test demonstrates that an array of a subclass with elements of a different subclass of the same parent
     * class is _not_ caught at compile time. It will only show up at run time. This is because arrays are _not_
     * type safe.
     * @throws IOException
     */
    @Test(expected = ArrayStoreException.class)
    public void testSavesAndLoadsArraysOfMixedType() throws IOException {
        Employee[] employees = new Employee[2];
        Person[] persons = employees;
        persons[0] = bertCooper;
        persons[1] = donDrapper;

        saver.saveAll(employees);

        loader.open();
        Assert.assertEquals(bertCooper, loader.load());
        Assert.assertEquals(donDrapper, loader.load());
        loader.close();
    }

    /**
     * This test shows how lists are _not_ covariant.
     * @throws IOException
     */
	@Test(expected = ClassCastException.class)
	public void testSavesAndLoadsListOfPeople() throws IOException {
		List<Person> persons = new ArrayList<>();
        persons.add(bertCooper);
        persons.add(donDrapper);
//        This will not work at compile time because saveAll requires List<Person> _not_ List<Partner>.
//        List<Partner> persons = new ArrayList<>(Arrays.asList(bertCooper, donDrapper));

		saver.saveAll(persons);

        loader.open();
		Assert.assertEquals(bertCooper, loader.load());
		Assert.assertEquals(donDrapper, loader.load());
        loader.close();
	}

    /**
     * This test shows how to use upper bounds. An upper bound says that a List can hold anything of a particular
     * base type. In this example it shows how the upper bound is Person so that we can create an array of Partner
     * and Employee without problem and have the type checked at compile time.
     * @throws IOException
     */
    @Test
    public void testSavesAndLoadsListOfUpperBoundPeople() throws IOException {
        List<Person> persons = new ArrayList<>(Arrays.asList(bertCooper, donDrapper, peggyOlson));

        saver.saveAllUpperBound(persons);

        loader.open();
        Assert.assertEquals(bertCooper, loader.load());
        Assert.assertEquals(donDrapper, loader.load());
        Assert.assertEquals(peggyOlson, loader.load());
        loader.close();
    }

    /**
     * Same as testSavesAndLoadsListOfUpperBoundPeople except uses generic type rather than an upper bound.
     * @throws IOException
     */
    @Test
    public void testSavesAndLoadsListOfGenericTypePeople() throws IOException {
        List<Person> persons = new ArrayList<>(Arrays.asList(bertCooper, donDrapper, peggyOlson));

        saver.saveAllGenericType(persons);

        loader.open();
        Assert.assertEquals(bertCooper, loader.load());
        Assert.assertEquals(donDrapper, loader.load());
        Assert.assertEquals(peggyOlson, loader.load());
        loader.close();
    }

    /**
     * ? super Class --> put/write/consume type of calls (loadAll) : contravariant (declare lower bound for type parameter)
     * ? extends Class --> get/read/produce type of calls (saveAll) : covariant (declare upper bound for type parameter)
     * <p>
     *     A good description can be found here:
     *     {@LINK http://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java}
     *     PECS
     *     Remember PECS: "Producer Extends, Consumer Super".
     *     <ul>
     *         <li>"Producer Extends" - If you need a List to produce T values (you want to read Ts from the list),
     *                                  you need to declare it with ? extends T, e.g. List&lt;? extends Integer&gt;. But
     *                                  you cannot add to this list.
     *         <li>"Consumer Super" - If you need a List to consume T values (you want to write Ts into the list),
     *                                  you need to declare it with ? super T, e.g. List&lt;? super Integer&gt;. But
     *                                  there are no guarantees what type of object you may read from this list.
     *         <li>If you need to both read from and write to a list, you need to declare it exactly with no wildcards,
     *                                  e.g. List&lt;Integer&gt;.
     *     </ul>
     * </p>
     *
     * @throws IOException
     */
    @Test
    public void testLoadsListsOfPeople() throws IOException {
        saver.save(bertCooper);
        saver.save(peggyOlson);

        List<Comparable<Person>> persons = new ArrayList<>();

        loader.open();
        loader.loadAll(persons);
        loader.close();

        Assert.assertEquals(bertCooper, persons.get(0));
        Assert.assertEquals(peggyOlson, persons.get(1));
    }
}
