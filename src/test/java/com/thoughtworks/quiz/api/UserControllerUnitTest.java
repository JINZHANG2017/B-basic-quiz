package com.thoughtworks.quiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerUnitTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<User> userJson;

    private User firstUser;

    @BeforeEach
    private void before(){
        firstUser=User.builder()
                .id(1L)
                .age(18L)
                .name("John")
                .avatar("avatar")
                .description("des")
                .build();
    }
    @Test
    public void should_get_a_user() throws Exception {
        when(userService.getUserById(1L)).thenReturn(firstUser);
        mockMvc.perform(get("/users/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.description", is("des")));
    }
    ObjectMapper objectMapper=new ObjectMapper();
    @Test
    public void should_add_a_user() throws Exception {
        User userPost=User.builder()
                .age(18L)
                .name("John")
                .avatar("avatar")
                .description("des")
                .build();
        when(userService.addUser(userPost)).thenReturn(firstUser);
        String json = objectMapper.writeValueAsString(userPost);
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.age", is(18)))
                .andExpect(jsonPath("$.description", is("des")));
    }
}