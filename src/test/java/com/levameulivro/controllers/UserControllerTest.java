package com.levameulivro.controllers;

import java.util.List;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.dto.UserResponseDTO;
import com.levameulivro.models.User;
import com.levameulivro.services.impl.UserServiceImp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes={LevameulivroApplication.class})
public class UserControllerTest {

    private static final Long ID = 1L;

    private static final String NAME = "Jorge Gonçalves de Oliveira";

    private static final String EMAIL = "dody60314@gmail.com";

    private static final String PASSWORD = "password";


    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImp userServiceImp;

    private User user;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }


    @Test
    void testAddUser() {

    }

    @Test
    void testDestroyUser() {

    }

    @Test
    void whenGetAllUserThenReturnAListOfUserResponseDTO() {
        Mockito.when(userServiceImp.findAllUser()).thenReturn(List.of(user));
    }

    @Test
    void whenGetUserByIdThenReturnSucess() {
        Mockito.when(userServiceImp.findUserById(Mockito.anyLong())).thenReturn(user);
        
        ResponseEntity<UserResponseDTO> response = userController.getUserById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserResponseDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
    }

    @Test
    void testUpdateUser() {

    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userResponseDTO = new UserResponseDTO(user);
    }
}
