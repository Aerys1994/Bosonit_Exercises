package com.Bosonit.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyProperties {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int myNumber;

    @Value("${new.property:no tiene valor}")
    private String newProperty;

    public void printProperties() {
        System.out.println("El valor de greeting es: " + greeting);
        System.out.println("El valor de my.number es: " + myNumber);
        System.out.println("El valor de new.property es: " + newProperty);
    }
}