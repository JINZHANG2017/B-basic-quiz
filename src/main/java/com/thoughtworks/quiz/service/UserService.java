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

    public User getUserById(Long id) {
        // TODO GTB-3: - 当方法返回值可能为空时，建议用Optional封装（repo层用了optional）
        User user = userRepository.getUserById(id);
        // TODO GTB-4: - 注意代码风格，需要适当加空格，可以使用IDEA的快捷键进行格式化(改）
        if (user == null) {
            // TODO GTB-4: - 抛异常时应该制定message(改）
            throw new UserNotFoundException("user not found");
        }
        // TODO GTB-4: - 该段代码可以不用else包围(改）
        List<Education> educationList = educationRepository.getAllByUserId(id);
        // TODO GTB-1: - 返回User时不用带出Education
        user.setEducations(educationList);
        return user;

    }

    public List<Education> getEducationsByUserId(Long id) {
        // TODO GTB-1: - 查询教育经历时，用户不存在应该抛异常
        getUserById(id);
        return educationRepository.getAllByUserId(id);
    }

    public User addUser(User user) {
        List<User> userList = userRepository.getAll();
        // TODO GTB-4: - 不必要的装箱，可以直接使用"(long) userList.size()"
        // TODO GTB-4: - 推荐使用单独的字段来保存最大的Id
        user.setId(Long.valueOf(userList.size() + 1));
        userRepository.addUser(user);
        return user;
    }

    public void addEducation(Long id, Education education) {
        // TODO GTB-1: - 添加教育经历时，用户不存在应该抛异常
        education.setUserId(id);
        educationRepository.addEducation(education);
    }
}
