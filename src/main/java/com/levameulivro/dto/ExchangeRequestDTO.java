package com.levameulivro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.Exchange;

import org.hibernate.validator.constraints.Length;

public class ExchangeRequestDTO {
    
    @NotNull @NotEmpty @Length(min = 5)
    private String nameBook;
    @NotNull @NotEmpty
    private String recipient;

    public ExchangeRequestDTO(Exchange exchange){
        this.nameBook = exchange.getBook().getName();
        this.recipient = exchange.getRecipient().getName();
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