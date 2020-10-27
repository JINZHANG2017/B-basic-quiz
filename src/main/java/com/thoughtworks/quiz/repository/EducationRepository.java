package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.entity.EducationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepository extends CrudRepository<EducationEntity,Long> {
    // TODO 用jparepository
    List<EducationEntity> findAll();
    List<EducationEntity> findAllByUserIdEquals(Long userId);
}
