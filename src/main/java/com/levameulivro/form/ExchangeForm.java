package com.levameulivro.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.levameulivro.models.Book;
import com.levameulivro.models.Exchange;
import com.levameulivro.models.User;
import com.levameulivro.repository.BookRepository;
import com.levameulivro.repository.ExchangeRepository;
import com.levameulivro.repository.UserRepository;

import org.hibernate.validator.constraints.Length;

public class ExchangeForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String nameBook;
    private String recipient;

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

    public Exchange update(Long id, ExchangeRepository exchangeRepository, BookRepository bookRepository, UserRepository userRepository){
        Exchange exchange = exchangeRepository.getById(id);
        Book book = bookRepository.findByName(nameBook);
        User user = userRepository.findByName(recipient);
        exchange.setBook(book);
        exchange.setRecipient(user);

        return exchange;
    }
    
    public Exchange convert(BookRepository bookRepository, UserRepository userRepository) {
        Book book = bookRepository.findByName(nameBook);
        User user = userRepository.findByName(recipient);
        return new Exchange(book, user);
    }
}
