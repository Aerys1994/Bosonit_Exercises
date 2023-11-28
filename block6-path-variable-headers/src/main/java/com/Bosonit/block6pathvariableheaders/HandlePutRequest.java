package com.Bosonit.block6pathvariableheaders;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HandlePutRequest {
    @PutMapping("/post")
    public Map<String, String> handlePutRequest(
            @RequestParam(name = "var1", defaultValue = "") String var1,
            @RequestParam(name = "var2", defaultValue = "") String var2
    ) {
        Map<String, String> resultMap = new HashMap<>();
        if (!var1.isEmpty()) {
            resultMap.put("var1", var1);
        }
        if (!var2.isEmpty()) {
            resultMap.put("var2", var2);
        }

        return resultMap;
    }
}
