package com.company;

import java.lang.reflect.Constructor;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //hashmap<key gender, value Person's list>
        //HashMap<Gender, List<Person>>

//        Constructor<Person> obj = Person.class.getConstructor();
//        Person obj1 = obj.newInstance();

//        List<Gender> g = Arrays.asList(Gender.values());
//        System.out.println(g.get(r.nextInt(3)));

        Random r = new Random();
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            people.add(new Person(generateRandomString(r.nextInt(10)), generateRandomString(r.nextInt(15)), r.nextInt(100), Arrays.asList(Gender.values()).get(r.nextInt(3))));
        }

        //Grouping by only gender
        Map<Gender, List<Person>> mapedPeople = people.stream()
                .collect(Collectors.groupingBy(each -> each.getGender()));

        System.out.println(mapedPeople.entrySet());


        //Grouping by gender, then by age
        Map<Gender, Map<Integer, List<Person>>> mapedPeople2 = people.stream()
                .collect(Collectors.groupingBy(each -> each.getGender(),
                        Collectors.groupingBy(each -> each.getAge())));

        System.out.println(mapedPeople2.entrySet());

        //mapedPeople.get(Gender.FEMALE).forEach(each -> System.out.println(each));

    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        char c = 97;
        StringBuilder sb = new StringBuilder();
        while (c <= 122) {
            sb.append(c);
            c++;
        }
        String letters = sb.toString();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(letters.charAt(random.nextInt(26)));
        }

        return stringBuilder.toString();
    }
}
