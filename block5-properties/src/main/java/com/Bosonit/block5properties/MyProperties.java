package com.Bosonit.block5properties;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties(prefix = "my")
public class MyProperties {

    private String greeting;

    private int number;

    @Value("${new.property:no tiene valor}")
    private String newProperty;

    @Value("${MYURL:default-url}")
    private String myUrl;

    public void printProperties() {
        System.out.println("El valor de greeting es: " + greeting);
        System.out.println("El valor de my.number es: " + number);
        System.out.println("El valor de new.property es: " + newProperty);
        System.out.println("MYURL from environment: " + myUrl);
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(String newProperty) {
        this.newProperty = newProperty;
    }

    public String getMyUrl() {
        return myUrl;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }
}