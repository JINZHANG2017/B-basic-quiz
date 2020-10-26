package com.thoughtworks.quiz.dto;


import com.thoughtworks.quiz.entity.EducationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
//    userId:long	用户 ID。
//    year:long	历年。
//    title:string	教育经历标题。
//    description:string	教育经历描述。
    private Long id;
    private Long userId;
    private Long year;
    private String title;
    private String description;

    public EducationEntity toEntity() {
        return EducationEntity.builder()
                .title(title)
                .year(year)
                .description(description)
                .build();
    }
}
