package com.levameulivro.controllers;

import com.levameulivro.repository.UserRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.levameulivro.controllers.dto.BookDto;
import com.levameulivro.form.BookForm;
import com.levameulivro.models.Book;
import com.levameulivro.repository.BookRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> detail(@PathVariable Long id){
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new BookDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookDto> add(@RequestBody @Valid BookForm form, UriComponentsBuilder uriBuilder){
        Book book = form.convert(userRepository);
        bookRepository.save(book);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookDto(book));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody @Valid BookForm form){
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            Book book = form.update(id, bookRepository, userRepository);
            return ResponseEntity.ok(new BookDto(book));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> destroy(@PathVariable Long id){
        Optional<Book> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
