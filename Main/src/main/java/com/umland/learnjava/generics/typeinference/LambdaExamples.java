package com.umland.learnjava.generics.typeinference;

import com.umland.learnjava.generics.implementgenerictype.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

public class LambdaExamples {
    public static void main(String[] args) {
        final Person donDrapper = new Person("Don Drapper", 89);
        final Person peggyOlson = new Person("Peggy Olson", 75);
        final Person bertCopper = new Person("Bert Copper", 100);

        List<Person> people = new ArrayList<>(Arrays.asList(donDrapper, peggyOlson, bertCopper));
        final Map<Boolean, List<Person>> oldAndYoung = people.stream().collect(partitioningBy(person -> person.getAge() > 80));
        final Map<Boolean, Long> oldAndYoungCnt = people.stream().collect(partitioningBy(person -> person.getAge() > 80, counting()));

        System.out.println(oldAndYoung);
        System.out.println(oldAndYoungCnt);
    }
}
