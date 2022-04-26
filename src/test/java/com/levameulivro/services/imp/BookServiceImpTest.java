package com.levameulivro.services.imp;

import com.levameulivro.dto.BookRequestDTO;

import java.util.List;
import java.util.Optional;

import com.levameulivro.LevameulivroApplication;
import com.levameulivro.exceptions.ObjectNotFoundException;
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

    private static final int INDEX = 0;

    private static final String NAME = "Harry Potter";

    private static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";

    private static final String AUTHOR = "JK.ROWLING";

    private static final Long ID = 1L;
    
    @InjectMocks
    private BookServiceImp bookServiceImp;

    @Mock
    private BookRepository bookRepository;

    private User user;
    private Book book;
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

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try{
            bookServiceImp.findBookById(ID);
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfBooks(){
        Mockito.when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> response = bookServiceImp.findAllBook();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Book.class, response.get(INDEX).getClass());

        Assertions.assertEquals(ID, response.get(INDEX).getId());
        Assertions.assertEquals(NAME, response.get(INDEX).getName());
        Assertions.assertEquals(AUTHOR, response.get(INDEX).getAuthor());

        Assertions.assertEquals("Jorge Gonçalves de Oliveira", response.get(INDEX).getOwner().getName());
        Assertions.assertEquals("dody60314@gmail.com", response.get(INDEX).getOwner().getEmail());
    }

    @Test
    void whenCreateThenReturnSucess(){
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book response = bookServiceImp.createBook(bookRequestDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Book.class, response.getClass());


        Assertions.assertEquals("Jorge Gonçalves de Oliveira", response.getOwner().getName());
        Assertions.assertEquals("dody60314@gmail.com", response.getOwner().getEmail());

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(AUTHOR, response.getAuthor());
    }

    private void startBook(){
        user = new User(ID, "Jorge Gonçalves de Oliveira", "dody60314@gmail.com", "password");
        book = new Book(ID, user, NAME, AUTHOR);
        bookRequestDTO = new BookRequestDTO(book);
        optionalBook = Optional.of(new Book(ID, user, NAME, AUTHOR));
    }
}
