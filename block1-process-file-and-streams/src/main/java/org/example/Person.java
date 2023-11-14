package org.example;

/* Con esta clase creamos un objeto Person cuyos atributos serán útiles posteriormente en el proceso
de filtrado.
 */
public class Person {

    private String name;
    private String town;
    private String age;

    public Person(String name, String town, String age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() { return this.name; }

    public String getAge() { return this.age; }

    public String getTown() { return town; }
}
