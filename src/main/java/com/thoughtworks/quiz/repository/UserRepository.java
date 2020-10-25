package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.Education;
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
        List<Education> educationList=new ArrayList<>();

        // TODO GTB-4: - 不用的代码应该及时清理(改）


        userList.add(new User(1L,"John",25L,"https://inews.gtimg.com/newsapp_match/0/3581582328/0","Possess rich project experience, good baiting becomes a habit and strong learning adaptability.",educationList));
    }
    public List<User> getAll(){
        return userList;
    }
    public User getUserById(Long id){
        Optional<User> userFound = userList.stream().filter(user -> user.getId() == id).findFirst();
        // TODO GTB-3: - 下面代码可以替换成一行"return userFound.orElse(null)"(改）
        return userFound.orElse(null);
//        if(userFound.isPresent()){
//            return userFound.get();
//        }else{
//            return null;
//        }
    }

    public List<Education> getEducations(Long id){
        Optional<User> userFound = userList.stream().filter(user -> user.getId() == id).findFirst();
        if(userFound.isPresent()){
            return userFound.get().getEducations();
        }else{
            return null;
        }
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
