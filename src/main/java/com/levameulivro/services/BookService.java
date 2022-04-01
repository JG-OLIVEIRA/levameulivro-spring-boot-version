package com.levameulivro.services;
import java.util.List;
import java.util.Optional;

import com.levameulivro.controllers.dto.BookRequestDTO;
import com.levameulivro.models.Book;


public interface BookService {
    List<Book> findAllBook();
    Optional<Book> findBookById(Long id);
    Book createBook(BookRequestDTO bookDto);
    Book updateBook(Long bookId, BookRequestDTO bookDTO);
    void deleteBookById(Long bookId);
}
