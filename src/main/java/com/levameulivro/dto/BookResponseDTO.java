package com.levameulivro.dto;

import com.levameulivro.models.Book;

public class BookResponseDTO {
  
    private Long id;
    private String owner;
    private String name;
    private String author;

    public BookResponseDTO(Book book){
        this.id = book.getId();
        this.owner = book.getOwner().getName();
        this.name = book.getName();
        this.author = book.getAuthor();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long bookId){
        this.id = bookId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    
}
