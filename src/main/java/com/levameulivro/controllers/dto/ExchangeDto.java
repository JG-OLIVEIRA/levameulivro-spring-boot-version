package com.levameulivro.controllers.dto;

import com.levameulivro.models.Exchange;

public class ExchangeDto {

    private String nameBook;
    private String recipient;

    public ExchangeDto(Exchange exchange){
        this.nameBook = exchange.getNameBook().getName();
        this.recipient = exchange.getRecipient().getName();
    }

    public String getName() {
        return nameBook;
    }

    public void setName(String name) {
        this.nameBook = name;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    
}
