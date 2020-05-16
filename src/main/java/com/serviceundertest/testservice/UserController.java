package com.serviceundertest.testservice;

import Resource.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final String CONTEXT = "/api/v1/User";
    //Note: Autowire will look for any instance of the service across the API and inject it
    //this is preferable to creating multiple instances of the service per controller
    @Autowired
    private UserService userService;
    @GetMapping(value = CONTEXT)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping(value = CONTEXT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user){
        userService.addUser(user);
    }
}