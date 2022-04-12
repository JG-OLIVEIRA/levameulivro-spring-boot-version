package com.levameulivro.services.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.repositories.UserRepository;
import com.levameulivro.services.impl.UserServiceImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImpTest {
    
    private static final Long ID = 1L;

    private static final String NAME = "Jorge Gonçalves de Oliveira";

    private static final String EMAIL = "dody60314@gmail.com";

    private static final String PASSWORD = "password";

    @InjectMocks
    private UserServiceImp service;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserRequestDTO userRequestDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance(){
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);

        Optional<User> response = service.findUserById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.get().getClass());
        assertEquals(ID, response.get().getId());
        assertEquals(NAME, response.get().getName());
        assertEquals(EMAIL, response.get().getEmail());
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRequestDTO = new UserRequestDTO(user);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}
