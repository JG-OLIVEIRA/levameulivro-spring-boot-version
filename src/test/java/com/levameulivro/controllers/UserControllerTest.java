package com.levameulivro.controllers;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.services.impl.UserServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={LevameulivroApplication.class})
public class UserControllerTest {

    private static final String EMAIL_JÁ_CADASTRADO = "Email já cadastrado";

    private static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";

    private static final Long ID = 1L;

    private static final String NAME = "Jorge Gonçalves de Oliveira";

    private static final String EMAIL = "dody60314@gmail.com";

    private static final String PASSWORD = "password";

    private static final int INDEX = 0;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImp userServiceImp;

    private User user;
    private UserRequestDTO userRequestDTO;

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
    void testGetAllUser() {

    }

    @Test
    void testGetUserById() {

    }

    @Test
    void testUpdateUser() {

    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRequestDTO = new UserRequestDTO(user);
    }
}
