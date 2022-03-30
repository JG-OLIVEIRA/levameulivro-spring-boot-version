package com.levameulivro.controllers.dto;

import com.levameulivro.models.Exchange;

public class ExchangeDto {

    private String book;
    private String recipient;

    public ExchangeDto(Exchange exchange){
        this.book = exchange.getBook().getName();
        this.recipient = exchange.getRecipient().getName();
    }

    public String getName() {
        return book;
    }

    public void setName(String name) {
        this.book = name;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    
}
