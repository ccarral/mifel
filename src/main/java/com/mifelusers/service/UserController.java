package com.mifelusers.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        User user = new User();
        user.setFirstName("Carlos");
        user.setLastName("Carral");
        user.setEmail("carloscarral13@gmail.com");
        user.setId(0);
        return user;
    }
}
