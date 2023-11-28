package com.Bosonit.block6pathvariableheaders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserId {

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable String id) {
        return "ID: " + id;
    }
}
