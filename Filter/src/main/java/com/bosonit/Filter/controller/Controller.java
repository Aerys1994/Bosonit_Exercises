package com.bosonit.Filter.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/add")
    public String addParametros(@RequestAttribute("parameters") String parameters) {
        return "You added one " + parameters;

    }
    @GetMapping("/{endPoint}/{parameter}={path}")
    public String endpoint(
            @PathVariable String endPoint,
            @PathVariable String parameter,
            @PathVariable String path) {
        return "Endpoint requested: \"" + endPoint +
                "\" with parameter: \"" + parameter +
                "\" and path: \"" + path + "\"";
    }

    @GetMapping("/greet/name")
    public String redirectToSaluda() {

        return greet();
    }

    @GetMapping("/greet")
    public String greet() {

        return "Hi from endpoint /greet";
    }
}