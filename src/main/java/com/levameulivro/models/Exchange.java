package com.levameulivro.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exchange {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book nameBook;
    @ManyToOne
    private User recipient;
    
    public Exchange(){

    }

    public Exchange(Book name, User recipiente){
        this.nameBook = name;
        this.recipient = recipiente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getNameBook() {
        return nameBook;
    }

    public void setNameBook(Book nameBook) {
        this.nameBook = nameBook;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

}
