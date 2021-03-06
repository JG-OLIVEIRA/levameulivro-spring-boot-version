package com.levameulivro.repositories;

import com.levameulivro.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
    String findByOwnerName(String ownername);
    Book findByName(String name);
}
