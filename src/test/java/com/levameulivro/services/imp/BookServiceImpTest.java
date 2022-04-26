package com.levameulivro.services.imp;

import com.levameulivro.dto.BookRequestDTO;
import java.util.Optional;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.dto.UserRequestDTO;
import com.levameulivro.models.Book;
import com.levameulivro.models.User;
import com.levameulivro.repositories.BookRepository;
import com.levameulivro.services.impl.BookServiceImp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes={LevameulivroApplication.class})
public class BookServiceImpTest {

    private static final String NAME = "Harry Potter";
    private static final String AUTHOR = "JK.ROWLING";

    private static final Long ID = 1L;
    
    @InjectMocks
    private BookServiceImp bookServiceImp;

    @Mock
    private BookRepository bookRepository;

    private User user;
    private Book book;
    private UserRequestDTO userRequestDTO;
    private BookRequestDTO bookRequestDTO;
    private Optional<Book> optionalBook;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startBook();
    }

    @Test
    void whenFindByIdThenReturnAnBookInstace(){
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(optionalBook);

        Book response = bookServiceImp.findBookById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Book.class, response.getClass());
        Assertions.assertEquals(User.class, response.getOwner().getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(AUTHOR, response.getAuthor());
        Assertions.assertEquals("Jorge Gonçalves de Oliveira", response.getOwner().getName());
        Assertions.assertEquals("dody60314@gmail.com", response.getOwner().getEmail());
        Assertions.assertEquals("password", response.getOwner().getPassword());
    }

    private void startBook(){
        user = new User(ID, "Jorge Gonçalves de Oliveira", "dody60314@gmail.com", "password");
        bookRequestDTO = new BookRequestDTO(new Book(ID, user, NAME, AUTHOR));
        optionalBook = Optional.of(new Book(ID, user, NAME, AUTHOR));
    }
}
