package com.levameulivro.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Exchange {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Book bookname;
    @ManyToOne
    private User username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBookname() {
        return bookname;
    }

    public void setBookname(Book bookname) {
        this.bookname = bookname;
    }

    public User getUsername() {
        return username;
    }
    
    public void setUsername(User username) {
        this.username = username;
    }

}
