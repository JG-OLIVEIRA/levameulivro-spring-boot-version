package com.levameulivro.services.imp;

import java.util.List;
import java.util.Optional;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.User;
import com.levameulivro.repositories.UserRepository;
import com.levameulivro.services.exceptions.ObjectNotFoundException;
import com.levameulivro.services.impl.UserServiceImp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={LevameulivroApplication.class})
class UserServiceImpTest {
    
    private static final int INDEX = 0;

    private static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";

    private static final Long ID = 1L;

    private static final String NAME = "Jorge Gonçalves de Oliveira";

    private static final String EMAIL = "dody60314@gmail.com";

    private static final String PASSWORD = "password";

    @InjectMocks
    private UserServiceImp userService;

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
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);

        User response = userService.findUserById(ID);

    

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try {
            userService.findUserById(ID);
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers(){
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAllUser();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(User.class, response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NAME, response.get(INDEX).getName());
        Assertions.assertEquals(EMAIL, response.get(INDEX).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRequestDTO = new UserRequestDTO(user);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}
