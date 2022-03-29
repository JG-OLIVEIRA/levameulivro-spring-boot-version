package com.levameulivro.controllers;

import com.levameulivro.repository.UserRepository;
import java.net.URI;
import java.util.List;

import com.levameulivro.controllers.dto.BookDto;
import com.levameulivro.form.BookForm;
import com.levameulivro.models.Book;
import com.levameulivro.repository.BookRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Book> show(){
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @PostMapping
    public ResponseEntity<BookDto> add(@RequestBody @Valid BookForm form, UriComponentsBuilder uriBuilder){
        Book book = form.convert(userRepository);
        bookRepository.save(book);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookDto(book));
    }

}
