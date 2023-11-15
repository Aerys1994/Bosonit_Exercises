package org.example;


import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> arguments = Arrays.asList(1, 2, 3, 4);

        arguments.forEach(System.out::println);
    }
}