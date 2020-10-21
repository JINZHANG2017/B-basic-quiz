package com.thoughtworks.quiz.api;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        Long id = userService.addUser(user);
        return "{\"id\":\""+id+"\"}";
    }
    @GetMapping("/{id}/educations")
    public List<Education> getUserEducations(@PathVariable Long id){
        return userService.getEducationsByUserId(id);
    }
}
