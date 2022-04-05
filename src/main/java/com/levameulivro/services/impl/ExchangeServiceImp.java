package com.levameulivro.services.impl;

import com.levameulivro.dto.ExchangeRequestDTO;
import com.levameulivro.models.Book;
import com.levameulivro.models.Exchange;
import com.levameulivro.models.User;
import com.levameulivro.repositories.BookRepository;
import com.levameulivro.repositories.ExchangeRepository;
import com.levameulivro.repositories.UserRepository;
import com.levameulivro.services.ExchangeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImp implements ExchangeService {
    
    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Exchange> findAllExchange(){
        return exchangeRepository.findAll();
    }

    @Override
    public Optional<Exchange> findExchangeById(Long exchangeId){
        Optional<Exchange> exchange = exchangeRepository.findById(exchangeId);
        return exchange;
    }

    @Override
    public Exchange createExchange(ExchangeRequestDTO exchangeDTO){
        Exchange exchange = new Exchange();
        User user = userRepository.findByName(exchangeDTO.getRecipient());
        Book book = bookRepository.findByName(exchangeDTO.getNameBook());
        exchange.setBook(book);
        exchange.setRecipient(user);
        return exchangeRepository.save(exchange);
    }

    @Override
    public Exchange updateExchange(Long exchangeId, ExchangeRequestDTO exchangeDTO){
        Optional<Exchange> optional = exchangeRepository.findById(exchangeId);
        if(optional.isPresent()){
            Exchange exchange = optional.get();
            Book book = bookRepository.findByName(exchangeDTO.getNameBook());
            User user = userRepository.findByName(exchangeDTO.getRecipient());
            exchange.setBook(book);
            exchange.setRecipient(user);
            return exchangeRepository.save(exchange);
        }
        return null;
    }

    @Override
    public void deleteExchangeById(Long exchangeId){
        exchangeRepository.deleteById(exchangeId);
    }
}
