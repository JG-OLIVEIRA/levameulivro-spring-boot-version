package com.levameulivro.services.impl;

import com.levameulivro.models.User;
import com.levameulivro.repositories.BookRepository;
import com.levameulivro.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import com.levameulivro.dto.BookRequestDTO;
import com.levameulivro.models.Book;
import com.levameulivro.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImp implements BookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        return book;
    }

    @Override
    public Book createBook(BookRequestDTO bookDTO){
        Book book = new Book();
        User user = userRepository.findByName(bookDTO.getOwnername());
        book.setOwner(user);
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookRequestDTO bookDTO){
        Optional<Book> optional = bookRepository.findById(bookId);
        if(optional.isPresent()){
            Book book = optional.get();
            User user = userRepository.findByName(bookDTO.getOwnername());
            book.setName(bookDTO.getName());
            book.setAuthor(bookDTO.getAuthor());
            book.setOwner(user);
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);;
    }
}
