package com.Bosonit.block6personcontrollers;

public interface PersonService {
    Person createPerson(String nombre, String poblacion, int edad);
    Person ageTimesTwo(Person person);
}
