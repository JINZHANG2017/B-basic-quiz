package com.thoughtworks.quiz.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "education")
public class EducationEntity {
    @Id
    @GeneratedValue
    private Long year;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
