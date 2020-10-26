package com.thoughtworks.quiz.entity;

import com.thoughtworks.quiz.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long age;
    private String avatar;
    private String description;

//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
//    private List<EducationEntity> educationEntities;

    public User toUser() {
        return new User(id, name, age, avatar, description);
    }
}
