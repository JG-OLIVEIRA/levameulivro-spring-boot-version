package com.levameulivro.controllers;

import java.util.ArrayList;
import java.util.List;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.dto.UserRequestDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes={LevameulivroApplication.class})
public class UserControllerTest {

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
    void whenAddUserThenReturnAdded() {
        Mockito.when(userServiceImp.createUser(Mockito.any())).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.addUser(userRequestDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void testDestroyUser() {

    }

    @Test
    void whenGetAllUserThenReturnAListOfUserResponseDTO() {
        Mockito.when(userServiceImp.findAllUser()).thenReturn(List.of(user));

        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUser();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
        Assertions.assertEquals(UserResponseDTO.class, response.getBody().get(INDEX).getClass());

        Assertions.assertEquals(ID, response.getBody().get(INDEX).getId());
        Assertions.assertEquals(NAME, response.getBody().get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
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
    void whenUpdateThenReturnSucess() {
        Mockito.when(userServiceImp.updateUser(userRequestDTO)).thenReturn(user);

        ResponseEntity<UserResponseDTO> response = userController.updateUser(ID, userRequestDTO);
        
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserResponseDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRequestDTO = new UserRequestDTO(user);
    }
}
