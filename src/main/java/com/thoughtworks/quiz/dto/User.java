package com.thoughtworks.quiz.dto;


import com.thoughtworks.quiz.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

//    id:long	ID。
//    name:string	名字。
//    age:long	年龄。
//    avatar:string	头像图片链接地址。
//    description:string
    private Long id;
    private String name;
    private Long age;
    private String avatar;
    private String description;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .age(age)
                .name(name)
                .avatar(avatar)
                .description(description)
                .build();
    }
}
