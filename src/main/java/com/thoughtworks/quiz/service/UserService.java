package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.exception.UserNotFoundException;
import com.thoughtworks.quiz.repository.EducationRepository;
import com.thoughtworks.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationRepository educationRepository;

    public User getUserById(Long id){
        User user = userRepository.getUserById(id);
        if(user==null){
            throw new UserNotFoundException();
        }else{
            List<Education> educationList = educationRepository.getAllByUserId(id);
            user.setEducations(educationList);
            return user;
        }
    }

    public List<Education> getEducationsByUserId(Long id) {
        return educationRepository.getAllByUserId(id);
    }

    public Long addUser(User user) {
        List<User> userList = userRepository.getAll();
        user.setId(Long.valueOf(userList.size()+1));
        userRepository.addUser(user);
        return user.getId();
    }

    public void addEducation(Long id,Education education) {
        education.setUserId(id);
        educationRepository.addEducation(education);
    }
}
