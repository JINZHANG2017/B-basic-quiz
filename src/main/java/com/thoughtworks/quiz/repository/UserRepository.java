package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public UserRepository(){
        userList.add(new User(1L,"user1",5L,"https://inews.gtimg.com/newsapp_match/0/3581582328/0","第一个用户"));
    }
    public User getUserById(Long id){
        Optional<User> userFound = userList.stream().filter(user -> user.getId() == id).findFirst();
        if(userFound.isPresent()){
            return userFound.get();
        }else{
            return null;
        }
    }
}
