package com.thoughtworks.quiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.repository.UserRepository;
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
    @Test
    public void should_get_user_by_id() throws Exception {
        mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.age", is(25)))
                .andExpect(jsonPath("$.educations",hasSize(3)));
    }

    @Test
    public void should_get_educations_by_userid() throws Exception {
        mockMvc
                .perform(get("/users/1/educations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[0].year",is(2010)));
    }

    @Test
    public void should_add_a_user() throws Exception {
        User user=new User("zhangsan",15L,"picdes","一个好人");
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(user);
        mockMvc
                .perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is("2")));
        List<User> userList = userRepository.getAll();
        assertEquals("一个好人",userList.get(1).getDescription());
    }
}