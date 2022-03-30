package com.levameulivro.controllers.dto;

import com.levameulivro.models.Book;

public class BookDto {
    
    private String owner;
    private String bookname;
    private String author;

    public BookDto(Book book){
        this.owner = book.getOwner().getName();
        this.bookname = book.getBookname();
        this.author = book.getAuthor();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
