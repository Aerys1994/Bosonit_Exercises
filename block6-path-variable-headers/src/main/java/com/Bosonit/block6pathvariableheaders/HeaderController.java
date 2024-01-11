package com.Bosonit.block6pathvariableheaders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public Map<String, String> handleGetRequestWithHeaders(
            @RequestHeader(name = "h1", defaultValue = "") String h1,
            @RequestHeader(name = "h2", defaultValue = "") String h2) {

        Map<String, String> resultMap = new HashMap<>();
        if (!h1.isEmpty()) {
            resultMap.put("h1", h1);
        }
        if (!h2.isEmpty()) {
            resultMap.put("h2", h2);
        }

        return resultMap;
    }
}
