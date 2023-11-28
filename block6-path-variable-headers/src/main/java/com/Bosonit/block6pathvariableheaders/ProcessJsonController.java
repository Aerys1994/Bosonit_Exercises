package com.Bosonit.block6pathvariableheaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessJsonController {
    @PostMapping("/processJson")
    public ResponseEntity<JsonRequest> processJson(@RequestBody JsonRequest jsonRequest) {
        return new ResponseEntity<>(jsonRequest, HttpStatus.OK);
    }
}
