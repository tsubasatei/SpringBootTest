package com.xt.springboot.controller;

import com.xt.springboot.entities.User;
import com.xt.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElse(new User());
        return user;
    }

    @GetMapping("/user")
    public User save(User user) {
        User user2 = userRepository.save(user);
        return user2;
    }
}
