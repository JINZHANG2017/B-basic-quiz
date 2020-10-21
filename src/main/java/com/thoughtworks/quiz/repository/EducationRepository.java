package com.thoughtworks.quiz.repository;

import com.thoughtworks.quiz.dto.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EducationRepository {
    private List<Education> educationList=new ArrayList<>();
    public List<Education> getAll(){
        return educationList;
    }
    public EducationRepository(){
        Education education1=new Education(1L,2010L,"First school","Serving as a cadre of the Department of External Relations of the Student Union, in the process of student work and outgoing sponsorship and business contacts, greatly improved his ability to handle affairs and affairs.");
        educationList.add(education1);
        Education education2=new Education(1L,2013L,"Second school","In normal school life, Yuan has done many part-time jobs. For example: family tutors, telephone interviewers, and summer jobs in factories. I have personally experienced the different operating procedures and methods of various jobs, and have become hard-working.");
        educationList.add(education2);
        Education education3=new Education(1L,2017L,"Third school","Study hard and earnestly, have excellent grades, and rank among the best. Excellent both in character and learning, won the college scholarship for three consecutive years.");
        educationList.add(education3);
    }

    public List<Education> getAllByUserId(Long userId){
        return educationList.stream().filter(education -> education.getUserId()==userId).collect(Collectors.toList());
    }

    public void addEducation(Education education) {
        educationList.add(education);
    }
}
