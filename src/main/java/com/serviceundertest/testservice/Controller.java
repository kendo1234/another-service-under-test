package com.serviceundertest.testservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
public class Controller {
    private final String CONTEXT = "/api/v1/example/{id}";
    @GetMapping(value = CONTEXT)
    @ResponseStatus(HttpStatus.OK)
    public String greeting(
            @PathVariable Long id,
            @RequestParam(required = true, defaultValue = "Unknown") String name,
            @RequestParam(required = false) String number) {
        if(number == null){
            return "Example content for " + name + " to the ID: "+id;
        }
        int num = Integer.parseInt(number);
        return "Example content for " + name + " with number " + (num + 3) + " to the ID: "+id;
    }
}
