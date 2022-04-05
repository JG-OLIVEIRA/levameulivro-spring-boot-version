package com.levameulivro.controllers.dto;

import com.levameulivro.models.Exchange;

public class ExchangeResponseDTO {

    private Long id;
    private String nameBook;
    private String recipient;

    public ExchangeResponseDTO(Exchange exchange){
        this.id = exchange.getId();
        this.nameBook = exchange.getBook().getName();
        this.recipient = exchange.getRecipient().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getRecipient() {
        return recipient;
    }
    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
}
