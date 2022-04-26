package com.levameulivro.controllers;

import com.levameulivro.services.impl.BookServiceImp;

import java.net.URI;
import java.util.List;
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

    private static final String ID = "/{bookId}";
    
    @Autowired
    private BookServiceImp bookServiceImp;

    @GetMapping
    public List<BookResponseDTO> getAllBook(){
        List<Book> books = bookServiceImp.findAllBook();
        return books.stream().map(BookResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping(value = ID)
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long bookId){
        
        try{
            Book book = bookServiceImp.findBookById(bookId);
            return ResponseEntity.ok(new BookResponseDTO(book));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody @Valid BookRequestDTO bookDTO, UriComponentsBuilder uriBuilder){
        Book book = bookServiceImp.createBook(bookDTO);

        URI uri = uriBuilder.path("/books/{bookId}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookResponseDTO(book));
    }

    @PutMapping(value = ID)
    @Transactional
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long bookId, @RequestBody @Valid BookRequestDTO bookDTO){
            bookServiceImp.findAllBook();
            bookDTO.setId(bookId);
            try{
                Book book = bookServiceImp.updateBook(bookId, bookDTO);
                return ResponseEntity.ok(new BookResponseDTO(book));
            } catch (Exception e){
                return ResponseEntity.notFound().build();
            }
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<BookResponseDTO> destroyBook(@PathVariable Long bookId){
        try{
            bookServiceImp.deleteBookById(bookId);
            return ResponseEntity.ok().build();
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        
    }
}
