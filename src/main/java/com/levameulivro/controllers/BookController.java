package com.levameulivro.controllers;

import com.levameulivro.services.impl.BookServiceImp;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.levameulivro.dto.BookRequestDTO;
import com.levameulivro.dto.BookResponseDTO;
import com.levameulivro.models.Book;

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
    private BookServiceImp bookServiceImp;

    @GetMapping
    public List<BookResponseDTO> getAllBooks(){
        List<Book> books = bookServiceImp.findAllBook();
        return books.stream().map(BookResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long bookId){
        Optional<Book> optional = bookServiceImp.findBookById(bookId);
        if(optional.isPresent()){
            return ResponseEntity.ok(new BookResponseDTO(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody @Valid BookRequestDTO bookDTO, UriComponentsBuilder uriBuilder){
        Book book = bookServiceImp.createBook(bookDTO);

        URI uri = uriBuilder.path("/books/{bookId}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookResponseDTO(book));
    }

    @PutMapping("/{bookId}")
    @Transactional
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long bookId, @RequestBody @Valid BookRequestDTO bookDTO){
        Optional<Book> optional = bookServiceImp.findBookById(bookId);
        if(optional.isPresent()){
            Book book = bookServiceImp.updateBook(bookId, bookDTO);
            return ResponseEntity.ok(new BookResponseDTO(book));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookRequestDTO> destroyBook(@PathVariable Long bookId){
        Optional<Book> optional = bookServiceImp.findBookById(bookId);
        if(optional.isPresent()){
            bookServiceImp.deleteBookById(bookId);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
