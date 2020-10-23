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

        // TODO GTB-4: - 不用的代码应该及时清理
//        Education education1=new Education(1L,2010L,"First school","Serving as a cadre of the Department of External Relations of the Student Union, in the process of student work and outgoing sponsorship and business contacts, greatly improved his ability to handle affairs and affairs.");
//        educationList.add(education1);
//        Education education2=new Education(1L,2013L,"Second school","In normal school life, Yuan has done many part-time jobs. For example: family tutors, telephone interviewers, and summer jobs in factories. I have personally experienced the different operating procedures and methods of various jobs, and have become hard-working.");
//        educationList.add(education2);
//        Education education3=new Education(1L,2017L,"Third school","Study hard and earnestly, have excellent grades, and rank among the best. Excellent both in character and learning, won the college scholarship for three consecutive years.");
//        educationList.add(education3);


        userList.add(new User(1L,"John",25L,"https://inews.gtimg.com/newsapp_match/0/3581582328/0","Possess rich project experience, good baiting becomes a habit and strong learning adaptability.",educationList));
    }
    public List<User> getAll(){
        return userList;
    }
    public User getUserById(Long id){
        Optional<User> userFound = userList.stream().filter(user -> user.getId() == id).findFirst();
        // TODO GTB-3: - 下面代码可以替换成一行"return userFound.orElse(null)"
        if(userFound.isPresent()){
            return userFound.get();
        }else{
            return null;
        }
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
