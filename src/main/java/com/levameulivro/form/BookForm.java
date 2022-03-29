package com.levameulivro.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.Book;
import com.levameulivro.models.User;
import com.levameulivro.repository.UserRepository;

import org.hibernate.validator.constraints.Length;

public class BookForm {

    @NotNull @NotEmpty @Length(min = 8)
    private String ownername;
    @NotNull @NotEmpty @Length(min = 5)
    private String bookname;
    @NotNull @NotEmpty @Length(min = 5)
    private String author;

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

    public Book convert(UserRepository userRepository) {
        User username = userRepository.findByName(ownername);
        return new Book(username, bookname, author);
    }

}
