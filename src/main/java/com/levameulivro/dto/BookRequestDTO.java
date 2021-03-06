package com.levameulivro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.Book;

import org.hibernate.validator.constraints.Length;

public class BookRequestDTO{

    private Long id;
    @NotNull @NotEmpty @Length(min = 8)
    private String ownername;
    @NotNull @NotEmpty @Length(min = 5)
    private String name;
    @NotNull @NotEmpty @Length(min = 5)
    private String author;

    public BookRequestDTO(Book book){
        this.id = book.getId();
        this.ownername = book.getOwner().getName();
        this.name = book.getName();
        this.author = book.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
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
