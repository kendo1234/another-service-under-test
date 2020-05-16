package com.serviceundertest.testservice;

import Resource.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>(Arrays.asList(
            new User("Rose"),
            new User("Poe"),
            new User("Finn")
    )
    );

    public List<User> getUsers(){
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }
}