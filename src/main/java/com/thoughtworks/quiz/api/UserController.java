package com.thoughtworks.quiz.api;

import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
