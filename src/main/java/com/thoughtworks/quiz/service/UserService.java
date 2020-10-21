package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.exception.UserNotFoundException;
import com.thoughtworks.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(Long id){
        User user = userRepository.getUserById(id);
        if(user==null){
            throw new UserNotFoundException();
        }else{
            return user;
        }
    }
}
