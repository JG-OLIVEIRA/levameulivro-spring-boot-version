package com.levameulivro.controllers.dto;

import com.levameulivro.models.Book;

public class BookDto {
    
    private String ownername;
    private String bookname;
    private String author;

    public BookDto(Book book){
        this.ownername = book.getOwnername().getName();
        this.bookname = book.getBookname();
        this.author = book.getAuthor();
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
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
