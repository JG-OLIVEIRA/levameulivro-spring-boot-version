package com.levameulivro.services;

import java.util.List;
import com.levameulivro.dto.BookRequestDTO;
import com.levameulivro.models.Book;

public interface BookService {
    List<Book> findAllBook();
    Book findBookById(Long bookId);
    Book createBook(BookRequestDTO bookDTO);
    Book updateBook(Long bookId, BookRequestDTO bookDTO);
    void deleteBookById(Long bookId);
}
