package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.entity.EducationEntity;
import com.thoughtworks.quiz.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    List<UserEntity> findAll();

}
