package org.example;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the route of the CSV file");
            return;
        }

        String filePath = args[0];

        try {
            List<Person> people = Reader.csvFileReader(filePath);

            // Filtrado menores de 25 años
            List<Person> underThan25 = people.stream()
                    .filter(person -> Integer.parseInt(person.getAge()) < 25)
                    .toList();
            printPeople(underThan25);

            // Filtrado personas cuyo nombre no empieza por A
            List<Person> notStartsWithA = people.stream()
                    .filter(person -> !person.getName().startsWith("A"))
                    .toList();
            printPeople(notStartsWithA);

            // Primer elemento con ciudad en Madrid si existe
            Optional<Person> firstMadrid = people.stream()
                    .filter(person -> "Madrid".equals(person.getTown()))
                    .findFirst();
            firstMadrid.ifPresent(person -> System.out.println("First Madrid person: " + person));

            // Primer elemento con ciudad en Barcelona si existe
            Optional<Person> firstBarcelona = people.stream()
                    .filter(person -> "Barcelona".equals(person.getTown()))
                    .findFirst();
            firstBarcelona.ifPresent(person -> System.out.println("First Barcelona person: " + person));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printPeople(List<Person> people) {
        for (Person person : people) {
            System.out.println("Name: " + person.getName() +
                    ". Town: " + person.getTown() +
                    ". Age: " + person.getAge()
            );
        }
    }
}