package com.Bosonit.block6personcontrollers;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person createPerson(String nombre, String poblacion, int edad)
    {
        Person person = new Person();
        person.setNombre(nombre);
        person.setEdad(edad);
        person.setPoblacion(poblacion);

        return person;
    }

    @Override
    public Person ageTimesTwo(Person person)
    {
        person.setEdad(person.getEdad() * 2);
        return person;
    }
}
