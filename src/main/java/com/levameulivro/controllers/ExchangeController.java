package com.levameulivro.controllers;

import com.levameulivro.controllers.dto.ExchangeDto;
import com.levameulivro.form.ExchangeForm;
import com.levameulivro.models.Exchange;
import com.levameulivro.repository.BookRepository;
import com.levameulivro.repository.ExchangeRepository;
import com.levameulivro.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/exchanges")
public class ExchangeController {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ExchangeRepository exchangeRepository;

    @GetMapping
    public List<Exchange> show(){
        List<Exchange> exchanges = exchangeRepository.findAll();
        return exchanges;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeDto> detail(@PathVariable Long id){
        Optional<Exchange> optional = exchangeRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new ExchangeDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExchangeDto> add(@RequestBody @Valid ExchangeForm form, UriComponentsBuilder uriBuilder){
        Exchange exchange = form.convert(bookRepository, userRepository);
        exchangeRepository.save(exchange);

        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(exchange.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExchangeDto(exchange));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExchangeDto> destroy(@PathVariable Long id){
        Optional<Exchange> optional = exchangeRepository.findById(id);
        if(optional.isPresent()){
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
