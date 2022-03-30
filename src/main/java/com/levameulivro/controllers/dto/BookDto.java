package com.levameulivro.controllers.dto;

import com.levameulivro.models.Book;

public class BookDto {
    
    private String owner;
    private String name;
    private String author;

    public BookDto(Book book){
        this.owner = book.getOwner().getName();
        this.name = book.getName();
        this.author = book.getAuthor();
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
