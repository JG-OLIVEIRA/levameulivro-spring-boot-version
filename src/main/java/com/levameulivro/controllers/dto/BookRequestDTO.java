package com.levameulivro.controllers.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BookRequestDTO{

    @NotNull @NotEmpty @Length(min = 8)
    private String ownername;
    @NotNull @NotEmpty @Length(min = 5)
    private String name;
    @NotNull @NotEmpty @Length(min = 5)
    private String author;

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
