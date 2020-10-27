package com.thoughtworks.quiz.service;

import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.entity.UserEntity;
import com.thoughtworks.quiz.repository.EducationRepository;
import com.thoughtworks.quiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private EducationRepository educationRepository;
    private UserEntity firstUser;
    @BeforeEach
    void setUp(){
        userService=new UserService(userRepository,educationRepository);
        firstUser= UserEntity.builder()
                .id(1L)
                .age(18L)
                .name("John")
                .avatar("avatar")
                .description("des")
                .build();
    }

    @Test
    void should_get_a_user(){
        when(userRepository.findById(1L)).thenReturn(Optional.of(firstUser));
        User user = userService.getUserById(1L);
        assertThat(user).isEqualTo(firstUser.toUser());
    }
}