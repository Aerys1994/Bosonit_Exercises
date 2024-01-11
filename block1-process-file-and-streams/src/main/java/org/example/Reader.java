package org.example;

import Exceptions.InvalidLineFormatException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Reader {

    public static Person parsePerson(String line) throws InvalidLineFormatException {
        /* El parsePerson agarra cada line enviada por el reader y usa cada dato en el
        constructor de Person para crear una instancia de Person y añadirla a una lista.
         */
        try {
            String[] parts = line.split(":");

            // Lanza error si falta el nombre
            if (parts.length < 1 || parts[0].isEmpty()) {
                throw new InvalidLineFormatException(line, InvalidLineFormatException.MISSING_NAME);
            }

            // Lanza error si falta alguno de los dos delimitadores ":"
            if (parts.length < 2 || (parts[1].isEmpty() && (parts.length < 3 || parts[2].isEmpty()))) {
                throw new InvalidLineFormatException(line, InvalidLineFormatException.MISSING_DELIMITER);
            }

            // Hace las transformaciones pertinentes y devuelve los valores por defecto en el caso
            // de que town o age estén vacíos
            String name = parts[0].trim();
            String town = (parts.length > 1 && !parts[1].isEmpty()) ? parts[1].trim() : "Unknown";
            String age = (parts.length > 2 && !parts[2].isEmpty()) ? parts[2].trim() : "Unknown";

            return new Person(name, town, age);
        } catch (InvalidLineFormatException e) {
            System.err.println("Error in line: " + e.getLine());
            System.err.println("Message: " + e.getMessage());
            throw e;
            }
        }

    // El lector usa parsePerson para obtener las variables para el constructor de Person
    // y añade las instancias a la lista
    public static List<Person> csvFileReader(String filePath) throws Exception {

        List<Person> people = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of(filePath));

        for (String line : lines) {
            try {
                Person person = parsePerson(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                System.err.println("Error in line: " + e.getLine());
                System.err.println("Message: " + e.getMessage());
            }
        }

        return people;
    }
}
