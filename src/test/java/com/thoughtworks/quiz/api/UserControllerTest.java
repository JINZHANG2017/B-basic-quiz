package com.thoughtworks.quiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.entity.EducationEntity;
import com.thoughtworks.quiz.entity.UserEntity;
import com.thoughtworks.quiz.repository.EducationRepository;
import com.thoughtworks.quiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EducationRepository educationRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        educationRepository.deleteAll();
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .age(25L)
                .description("des")
                .build();
        userRepository.save(userEntity);
        mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.description", is("des")));
    }


    @Test
    public void should_get_404_when_user_not_exists() throws Exception {
        mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_get_educations_by_userid() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .age(25L)
                .description("des")
                .build();
        EducationEntity educationEntity1 = EducationEntity.builder()
                .title("education1")
                .description("descrip1")
                .year(2013L)
                .user(userEntity)
                .build();
        EducationEntity educationEntity2 = EducationEntity.builder()
                .title("education2")
                .description("descrip2")
                .year(2017L)
                .user(userEntity)
                .build();
        userRepository.save(userEntity);
        educationRepository.save(educationEntity1);
        educationRepository.save(educationEntity2);
        mockMvc
                .perform(get("/users/1/educations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].year", is(2013)))
                .andExpect(jsonPath("$[1].year", is(2017)));
    }

    @Test
    public void should_add_a_user() throws Exception {
        User user = User.builder()
                .age(15L)
                .description("一个好人")
                .avatar("picdes")
                .name("zhangsan")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc
                .perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
        List<UserEntity> userList = userRepository.findAll();
        assertEquals("一个好人", userList.get(0).getDescription());
    }

    @Test
    public void should_add_an_education() throws Exception {
        UserEntity user = UserEntity.builder()
                .age(15L)
                .description("一个好人")
                .avatar("picdes")
                .name("zhangsan")
                .build();
        userRepository.save(user);
        Education education = new Education();
        education.setTitle("fourth school");
        education.setDescription("act well");
        education.setYear(2020L);
        education.setUserId(user.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(education);
        mockMvc
                .perform(post("/users/1/educations").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        List<EducationEntity> educationEntityList = educationRepository.findAllByUserIdEquals(user.getId());
        assertEquals(1,educationEntityList.size());
        assertEquals("fourth school",educationEntityList.get(0).getTitle());
    }
}