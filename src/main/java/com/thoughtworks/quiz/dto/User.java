package com.thoughtworks.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
