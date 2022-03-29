package com.levameulivro.repository;

import com.levameulivro.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
    String findByownername(String ownername);
}
