package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.entity.EducationEntity;
import com.thoughtworks.quiz.entity.UserEntity;
import com.thoughtworks.quiz.exception.UserNotFoundException;
import com.thoughtworks.quiz.repository.EducationRepository;
import com.thoughtworks.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public User getUserById(Long id) {
        // TODO GTB-3: - 当方法返回值可能为空时，建议用Optional封装（repo层用了optional）
        UserEntity user = userRepository.findById(id).orElse(null);
        // TODO GTB-4: - 注意代码风格，需要适当加空格，可以使用IDEA的快捷键进行格式化(改）
        if (user == null) {
            // TODO GTB-4: - 抛异常时应该制定message(改）
            throw new UserNotFoundException("user not found");
        }
        // TODO GTB-4: - 该段代码可以不用else包围(改）
//        List<Education> educationList = educationRepository.getAllByUserId(id);
        // TODO GTB-1: - 返回User时不用带出Education
//        user.setEducations(educationList);
        return user.toUser();

    }

    public List<Education> getEducationsByUserId(Long id) {
        // TODO GTB-1: - 查询教育经历时，用户不存在应该抛异常
        getUserById(id);
        List<EducationEntity> foundList = educationRepository.findAllByUserIdEquals(id);
        List<Education> reList=new ArrayList<>();
        foundList.forEach(educationEntity -> {
            reList.add(educationEntity.toEducation());
        });
        return reList;
    }

    public User addUser(User user) {
        // TODO GTB-4: - 不必要的装箱，可以直接使用"(long) userList.size()"
        // TODO GTB-4: - 推荐使用单独的字段来保存最大的Id
        UserEntity userEntity = user.toEntity();
        userRepository.save(userEntity);
        user.setId(userEntity.getId());
        return user;
    }

    public void addEducation(Long id, Education education) {
        // TODO GTB-1: - 添加教育经历时，用户不存在应该抛异常
        Optional<UserEntity> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            EducationEntity educationEntity = education.toEntity();
            educationEntity.setUser(userFound.get());
            educationRepository.save(educationEntity);
        }else{
            throw new UserNotFoundException("user not found");
        }

    }
}
